import axios from 'axios'
import { useAuthStore } from '@/stores/auth'
import router from '@/router'

// Create axios instance
const apiClient = axios.create({
  baseURL: '/api',
  timeout: 30000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// Request interceptor - Add JWT token to requests
apiClient.interceptors.request.use(
  config => {
    const authStore = useAuthStore()
    const token = authStore.accessToken
    
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    
    // Add CSRF token if available
    const csrfToken = localStorage.getItem('csrf_token')
    if (csrfToken) {
      config.headers['X-CSRF-Token'] = csrfToken
    }
    
    return config
  },
  error => {
    console.error('Request error:', error)
    return Promise.reject(error)
  }
)

// Response interceptor - Handle errors and token refresh
apiClient.interceptors.response.use(
  response => response,
  async error => {
    const originalRequest = error.config
    const authStore = useAuthStore()
    
    // Handle 401 Unauthorized
    if (error.response?.status === 401) {
      // If not already trying to refresh token
      if (!originalRequest._retry) {
        originalRequest._retry = true
        
        try {
          // Try to refresh token
          await authStore.refreshToken()
          
          // Retry original request with new token
          originalRequest.headers.Authorization = `Bearer ${authStore.accessToken}`
          return apiClient(originalRequest)
        } catch (refreshError) {
          // Refresh failed, logout user
          authStore.logout()
          router.push({ name: 'Login', query: { redirect: router.currentRoute.value.fullPath } })
          return Promise.reject(refreshError)
        }
      }
    }
    
    // Handle 403 Forbidden
    if (error.response?.status === 403) {
      // Redirect to forbidden page or show message
      if (router.currentRoute.value.name !== 'Forbidden') {
        router.push({ name: 'Forbidden' })
      }
    }
    
    // Handle network errors
    if (!error.response) {
      console.error('Network error - please check your connection')
    }
    
    return Promise.reject(error)
  }
)

export default apiClient
