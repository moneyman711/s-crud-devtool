<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <!-- Stats Cards -->
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon blue">
              <el-icon :size="32"><User /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.totalUsers }}</div>
              <div class="stat-label">Total Users</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon green">
              <el-icon :size="32"><CircleCheck /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.activeUsers }}</div>
              <div class="stat-label">Active Users</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon orange">
              <el-icon :size="32"><Warning /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.inactiveUsers }}</div>
              <div class="stat-label">Inactive Users</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon purple">
              <el-icon :size="32"><TrendCharts /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.newThisMonth }}</div>
              <div class="stat-label">New This Month</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Recent Activity -->
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="16">
        <el-card shadow="always">
          <template #header>
            <div class="card-header">
              <span>Recent Site Managers</span>
              <el-button type="primary" size="small" @click="$router.push('/sitemanager')">
                View All
              </el-button>
            </div>
          </template>
          
          <el-table :data="recentUsers" style="width: 100%" :height="300">
            <el-table-column prop="username" label="Username" />
            <el-table-column prop="fullName" label="Full Name" />
            <el-table-column prop="email" label="Email" />
            <el-table-column prop="loginRole" label="Role">
              <template #default="{ row }">
                <el-tag :type="getRoleType(row.loginRole)" size="small">
                  {{ row.loginRole }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="currentStatus" label="Status" width="80">
              <template #default="{ row }">
                <el-tag :type="row.currentStatus === 1 ? 'success' : 'danger'" size="small">
                  {{ row.currentStatus === 1 ? 'Active' : 'Inactive' }}
                </el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card shadow="always">
          <template #header>
            <span>Quick Actions</span>
          </template>
          
          <div class="quick-actions">
            <el-button type="primary" class="action-btn" @click="$router.push('/sitemanager/create')">
              <el-icon><Plus /></el-icon>
              <span>Add User</span>
            </el-button>
            
            <el-button type="success" class="action-btn" @click="$router.push('/profile')">
              <el-icon><User /></el-icon>
              <span>My Profile</span>
            </el-button>
            
            <el-button type="warning" class="action-btn" @click="$router.push('/change-password')">
              <el-icon><Lock /></el-icon>
              <span>Change Password</span>
            </el-button>
            
            <el-button type="info" class="action-btn" @click="refreshData">
              <el-icon><Refresh /></el-icon>
              <span>Refresh Data</span>
            </el-button>
          </div>
        </el-card>
        
        <el-card shadow="always" style="margin-top: 20px;">
          <template #header>
            <span>System Info</span>
          </template>
          
          <el-descriptions :column="1" size="small">
            <el-descriptions-item label="Version">1.0.0</el-descriptions-item>
            <el-descriptions-item label="Environment">{{ environment }}</el-descriptions-item>
            <el-descriptions-item label="Last Login">
              {{ lastLoginTime }}
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { sitemanagerApi, dashboardApi } from '@/api'
import { ElMessage } from 'element-plus'

const stats = reactive({
  totalUsers: 0,
  activeUsers: 0,
  inactiveUsers: 0,
  newThisMonth: 0
})

const recentUsers = ref([])
const loading = ref(false)
const environment = ref(import.meta.env.MODE || 'development')
const lastLoginTime = ref(new Date().toLocaleString())

const getRoleType = (role) => {
  const types = {
    'ADMIN': 'danger',
    'SUPERADMIN': 'danger',
    'MANAGER': 'warning',
    'USER': 'info'
  }
  return types[role] || 'info'
}

const loadStats = async () => {
  try {
    const response = await dashboardApi.getStats()
    Object.assign(stats, response.data)
  } catch (error) {
    console.error('Failed to load stats:', error)
    // Fallback - load from sitemanager list
    try {
      const usersResponse = await sitemanagerApi.getAll({ size: 100 })
      const users = usersResponse.data.content || usersResponse.data
      
      stats.totalUsers = users.length
      stats.activeUsers = users.filter(u => u.currentStatus === 1).length
      stats.inactiveUsers = users.filter(u => u.currentStatus === 0).length
      
      // Simple calculation for new users this month
      const thisMonth = new Date().getMonth()
      const thisYear = new Date().getFullYear()
      stats.newThisMonth = users.filter(u => {
        const createdDate = new Date(u.createdDateTime || u.updateDateTime)
        return createdDate.getMonth() === thisMonth && createdDate.getFullYear() === thisYear
      }).length
    } catch (e) {
      console.error('Fallback stats failed:', e)
    }
  }
}

const loadRecentUsers = async () => {
  try {
    const response = await sitemanagerApi.getAll({ 
      page: 0, 
      size: 5,
      sort: 'updateDateTime,desc'
    })
    recentUsers.value = response.data.content || response.data || []
  } catch (error) {
    console.error('Failed to load recent users:', error)
  }
}

const refreshData = async () => {
  loading.value = true
  try {
    await Promise.all([loadStats(), loadRecentUsers()])
    ElMessage.success('Data refreshed successfully')
  } catch (error) {
    ElMessage.error('Failed to refresh data')
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  loadStats()
  loadRecentUsers()
})
</script>

<style lang="scss" scoped>
.dashboard {
  .stat-card {
    border-radius: 8px;
    
    .stat-content {
      display: flex;
      align-items: center;
      gap: 16px;
      
      .stat-icon {
        width: 64px;
        height: 64px;
        border-radius: 12px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: white;
        
        &.blue { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }
        &.green { background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%); }
        &.orange { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); }
        &.purple { background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%); }
      }
      
      .stat-info {
        flex: 1;
        
        .stat-value {
          font-size: 28px;
          font-weight: 600;
          color: #333;
        }
        
        .stat-label {
          font-size: 14px;
          color: #666;
          margin-top: 4px;
        }
      }
    }
  }
  
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .quick-actions {
    display: flex;
    flex-direction: column;
    gap: 12px;
    
    .action-btn {
      width: 100%;
      display: flex;
      align-items: center;
      gap: 8px;
      justify-content: flex-start;
    }
  }
}
</style>
