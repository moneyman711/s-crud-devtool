import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { requiresGuest: true }
  },
  {
    path: '/',
    component: () => import('@/layouts/AdminLayout.vue'),
    redirect: '/dashboard',
    meta: { requiresAuth: true },
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/Dashboard.vue'),
        meta: { title: 'Dashboard' }
      },
      {
        path: 'sitemanager',
        name: 'SiteManager',
        component: () => import('@/views/sitemanager/SitemanagerList.vue'),
        meta: { 
          title: 'Site Managers',
          permission: 'MODULE_SITEMANAGER_READ'
        }
      },
      {
        path: 'sitemanager/create',
        name: 'SitemanagerCreate',
        component: () => import('@/views/sitemanager/SitemanagerForm.vue'),
        meta: { 
          title: 'Create Site Manager',
          permission: 'MODULE_SITEMANAGER_WRITE'
        }
      },
      {
        path: 'sitemanager/:id/edit',
        name: 'SitemanagerEdit',
        component: () => import('@/views/sitemanager/SitemanagerForm.vue'),
        meta: { 
          title: 'Edit Site Manager',
          permission: 'MODULE_SITEMANAGER_WRITE'
        }
      },
      {
        path: 'profile',
        name: 'Profile',
        component: () => import('@/views/Profile.vue'),
        meta: { title: 'My Profile' }
      },
      {
        path: 'change-password',
        name: 'ChangePassword',
        component: () => import('@/views/ChangePassword.vue'),
        meta: { title: 'Change Password' }
      }
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: () => import('@/views/NotFound.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Navigation guards
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  
  // Set page title
  document.title = to.meta.title ? `${to.meta.title} - SiteManager` : 'SiteManager'
  
  // Check if route requires authentication
  if (to.meta.requiresAuth) {
    if (!authStore.isAuthenticated) {
      next({ name: 'Login', query: { redirect: to.fullPath } })
      return
    }
    
    // Check permissions if required
    if (to.meta.permission && !authStore.hasPermission(to.meta.permission)) {
      next({ name: 'Forbidden' })
      return
    }
  }
  
  // Redirect logged-in users away from login page
  if (to.meta.requiresGuest && authStore.isAuthenticated) {
    next({ name: 'Dashboard' })
    return
  }
  
  next()
})

export default router
