<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-header">
        <img src="/vite.svg" alt="Logo" class="logo" />
        <h1 class="title">SiteManager</h1>
        <p class="subtitle">Admin Portal Login</p>
      </div>

      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        @submit.prevent="handleLogin"
      >
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="Username"
            size="large"
            prefix-icon="User"
            autocomplete="username"
          />
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="Password"
            size="large"
            prefix-icon="Lock"
            show-password
            autocomplete="current-password"
            @keyup.enter="handleLogin"
          />
        </el-form-item>

        <el-form-item>
          <div class="login-options">
            <el-checkbox v-model="loginForm.rememberMe">Remember me</el-checkbox>
            <el-link type="primary" :underline="false" @click="showForgotPassword = true">
              Forgot password?
            </el-link>
          </div>
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            size="large"
            :loading="loading"
            class="login-button"
            native-type="submit"
          >
            Sign In
          </el-button>
        </el-form-item>
      </el-form>

      <div class="login-footer">
        <p>&copy; {{ currentYear }} SiteManager. All rights reserved.</p>
      </div>
    </div>

    <!-- Forgot Password Dialog -->
    <el-dialog
      v-model="showForgotPassword"
      title="Forgot Password"
      width="400px"
    >
      <el-form ref="forgotFormRef" :model="forgotForm" :rules="forgotRules">
        <el-alert
          type="info"
          :closable="false"
          class="mb-4"
        >
          Enter your email address and we'll send you instructions to reset your password.
        </el-alert>
        
        <el-form-item prop="email">
          <el-input
            v-model="forgotForm.email"
            placeholder="Enter your email"
            prefix-icon="Message"
          />
        </el-form-item>
      </el-form>
      
      <template #footer>
        <el-button @click="showForgotPassword = false">Cancel</el-button>
        <el-button type="primary" :loading="sendingReset" @click="handleForgotPassword">
          Send Reset Link
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage, ElNotification } from 'element-plus'
import { useAuthStore } from '@/stores/auth'
import { authApi } from '@/api'

const router = useRouter()
const route = useRoute()
const authStore = useAuthStore()

const loginFormRef = ref(null)
const forgotFormRef = ref(null)

const loading = ref(false)
const sendingReset = ref(false)
const showForgotPassword = ref(false)

const loginForm = reactive({
  username: '',
  password: '',
  rememberMe: false
})

const forgotForm = reactive({
  email: ''
})

const loginRules = {
  username: [
    { required: true, message: 'Please enter username', trigger: 'blur' }
  ],
  password: [
    { required: true, message: 'Please enter password', trigger: 'blur' },
    { min: 6, message: 'Password must be at least 6 characters', trigger: 'blur' }
  ]
}

const forgotRules = {
  email: [
    { required: true, message: 'Please enter email', trigger: 'blur' },
    { type: 'email', message: 'Please enter valid email', trigger: 'blur' }
  ]
}

const currentYear = computed(() => new Date().getFullYear())

const handleLogin = async () => {
  if (!loginFormRef.value) return
  
  await loginFormRef.value.validate(async (valid) => {
    if (!valid) return
    
    loading.value = true
    
    try {
      await authStore.login({
        username: loginForm.username,
        password: loginForm.password
      })
      
      ElNotification({
        title: 'Success',
        message: 'Welcome back!',
        type: 'success'
      })
      
      // Redirect to intended page or dashboard
      const redirect = route.query.redirect || '/dashboard'
      router.push(redirect)
    } catch (error) {
      console.error('Login error:', error)
      ElMessage.error(
        error.response?.data?.message || 'Login failed. Please check your credentials.'
      )
    } finally {
      loading.value = false
    }
  })
}

const handleForgotPassword = async () => {
  if (!forgotFormRef.value) return
  
  await forgotFormRef.value.validate(async (valid) => {
    if (!valid) return
    
    sendingReset.value = true
    
    try {
      await authApi.forgotPassword(forgotForm.email)
      
      ElNotification({
        title: 'Success',
        message: 'Password reset link sent to your email',
        type: 'success'
      })
      
      showForgotPassword.value = false
      forgotForm.email = ''
    } catch (error) {
      console.error('Forgot password error:', error)
      ElMessage.error(
        error.response?.data?.message || 'Failed to send reset link'
      )
    } finally {
      sendingReset.value = false
    }
  })
}
</script>

<style lang="scss" scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-box {
  width: 100%;
  max-width: 420px;
  background: white;
  border-radius: 16px;
  padding: 48px 40px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
}

.login-header {
  text-align: center;
  margin-bottom: 32px;
  
  .logo {
    width: 64px;
    height: 64px;
    margin-bottom: 16px;
  }
  
  .title {
    font-size: 28px;
    font-weight: 600;
    color: #333;
    margin: 0 0 8px 0;
  }
  
  .subtitle {
    font-size: 14px;
    color: #666;
    margin: 0;
  }
}

.login-form {
  .login-options {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
  }
  
  .login-button {
    width: 100%;
    height: 44px;
    font-size: 16px;
  }
}

.login-footer {
  margin-top: 32px;
  text-align: center;
  
  p {
    font-size: 12px;
    color: #999;
    margin: 0;
  }
}

.mb-4 {
  margin-bottom: 16px;
}
</style>
