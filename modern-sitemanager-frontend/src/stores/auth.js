import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { authApi } from '@/api'
import CryptoJS from 'crypto-js'

export const useAuthStore = defineStore('auth', () => {
  // State
  const accessToken = ref(localStorage.getItem('access_token') || null)
  const refreshTokenValue = ref(localStorage.getItem('refresh_token') || null)
  const user = ref(null)
  const permissions = ref([])
  const roles = ref([])
  
  // Getters
  const isAuthenticated = computed(() => !!accessToken.value)
  const currentUser = computed(() => user.value)
  const hasPermission = computed(() => (permission) => {
    if (!permissions.value) return false
    return permissions.value.includes(permission) || 
           permissions.value.includes('ROLE_SUPERADMIN')
  })
  const hasRole = computed(() => (role) => {
    if (!roles.value) return false
    return roles.value.includes(role)
  })
  
  // Actions
  async function login(credentials) {
    try {
      // Hash password before sending (additional security layer)
      const hashedCredentials = {
        ...credentials,
        password: CryptoJS.SHA256(credentials.password).toString()
      }
      
      const response = await authApi.login(hashedCredentials)
      const { accessToken: token, refreshToken, user: userData } = response.data
      
      // Store tokens
      setTokens(token, refreshToken)
      
      // Set user data
      setUser(userData)
      
      return response.data
    } catch (error) {
      console.error('Login failed:', error)
      throw error
    }
  }
  
  async function logout() {
    try {
      await authApi.logout()
    } catch (error) {
      console.error('Logout error:', error)
    } finally {
      clearAuth()
    }
  }
  
  async function refreshToken() {
    try {
      const response = await authApi.refreshToken()
      const { accessToken: token, refreshToken: newRefreshToken } = response.data
      
      setTokens(token, newRefreshToken)
      
      return token
    } catch (error) {
      console.error('Token refresh failed:', error)
      clearAuth()
      throw error
    }
  }
  
  async function checkAuth() {
    if (!accessToken.value) {
      return false
    }
    
    try {
      const response = await authApi.getCurrentUser()
      setUser(response.data)
      return true
    } catch (error) {
      console.error('Auth check failed:', error)
      clearAuth()
      return false
    }
  }
  
  async function changePassword(passwordData) {
    try {
      // Hash passwords before sending
      const hashedData = {
        currentPassword: CryptoJS.SHA256(passwordData.currentPassword).toString(),
        newPassword: CryptoJS.SHA256(passwordData.newPassword).toString(),
        confirmPassword: CryptoJS.SHA256(passwordData.confirmPassword).toString()
      }
      
      const response = await authApi.changePassword(hashedData)
      return response.data
    } catch (error) {
      console.error('Change password failed:', error)
      throw error
    }
  }
  
  // Private helper functions
  function setTokens(token, refresh) {
    accessToken.value = token
    refreshTokenValue.value = refresh
    
    localStorage.setItem('access_token', token)
    localStorage.setItem('refresh_token', refresh)
    
    // Set token expiry (optional)
    const expiry = Date.now() + 24 * 60 * 60 * 1000 // 24 hours
    localStorage.setItem('token_expiry', expiry.toString())
  }
  
  function setUser(userData) {
    user.value = userData
    permissions.value = userData.authorities || []
    
    // Extract roles from authorities
    roles.value = userData.authorities
      ? userData.authorities.filter(auth => auth.startsWith('ROLE_'))
      : []
  }
  
  function clearAuth() {
    accessToken.value = null
    refreshTokenValue.value = null
    user.value = null
    permissions.value = []
    roles.value = []
    
    localStorage.removeItem('access_token')
    localStorage.removeItem('refresh_token')
    localStorage.removeItem('token_expiry')
  }
  
  return {
    // State
    accessToken,
    refreshToken: refreshTokenValue,
    user,
    permissions,
    roles,
    
    // Getters
    isAuthenticated,
    currentUser,
    hasPermission,
    hasRole,
    
    // Actions
    login,
    logout,
    refreshToken,
    checkAuth,
    changePassword
  }
})
