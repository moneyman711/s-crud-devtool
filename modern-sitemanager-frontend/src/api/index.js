import apiClient from './client'

export const authApi = {
  login(credentials) {
    return apiClient.post('/auth/login', credentials)
  },
  
  logout() {
    return apiClient.post('/auth/logout')
  },
  
  refreshToken() {
    return apiClient.post('/auth/refresh')
  },
  
  getCurrentUser() {
    return apiClient.get('/auth/me')
  },
  
  changePassword(passwordData) {
    return apiClient.put('/auth/change-password', passwordData)
  },
  
  forgotPassword(email) {
    return apiClient.post('/auth/forgot-password', { email })
  },
  
  resetPassword(token, newPassword) {
    return apiClient.post('/auth/reset-password', { token, newPassword })
  }
}

export const sitemanagerApi = {
  getAll(params) {
    return apiClient.get('/sitemanagers', { params })
  },
  
  getById(id) {
    return apiClient.get(`/sitemanagers/${id}`)
  },
  
  create(data) {
    return apiClient.post('/sitemanagers', data)
  },
  
  update(id, data) {
    return apiClient.put(`/sitemanagers/${id}`, data)
  },
  
  delete(id) {
    return apiClient.delete(`/sitemanagers/${id}`)
  },
  
  search(query) {
    return apiClient.get('/sitemanagers/search', { params: { q: query } })
  },
  
  uploadPhoto(id, formData) {
    return apiClient.post(`/sitemanagers/${id}/photo`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  },
  
  getAuthorizations(id) {
    return apiClient.get(`/sitemanagers/${id}/authorizations`)
  },
  
  updateAuthorizations(id, authorizations) {
    return apiClient.put(`/sitemanagers/${id}/authorizations`, authorizations)
  }
}

export const dashboardApi = {
  getStats() {
    return apiClient.get('/dashboard/stats')
  },
  
  getActivityLog(params) {
    return apiClient.get('/dashboard/activity-log', { params })
  }
}
