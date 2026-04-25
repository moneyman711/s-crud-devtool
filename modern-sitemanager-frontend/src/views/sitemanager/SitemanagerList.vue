<template>
  <div class="sitemanager-list">
    <el-card shadow="never">
      <!-- Search and Actions Bar -->
      <div class="toolbar">
        <div class="search-box">
          <el-input
            v-model="searchQuery"
            placeholder="Search by name, username, email..."
            prefix-icon="Search"
            clearable
            style="width: 300px"
            @keyup.enter="handleSearch"
          />
          <el-button type="primary" @click="handleSearch">Search</el-button>
          <el-button @click="resetSearch">Reset</el-button>
        </div>
        
        <div class="actions">
          <el-button type="primary" @click="$router.push('/sitemanager/create')">
            <el-icon><Plus /></el-icon> Add New
          </el-button>
          <el-button @click="refreshList">
            <el-icon><Refresh /></el-icon> Refresh
          </el-button>
        </div>
      </div>

      <!-- Data Table -->
      <el-table
        v-loading="loading"
        :data="tableData"
        stripe
        border
        style="width: 100%; margin-top: 20px"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="username" label="Username" width="150" sortable />
        <el-table-column prop="fullName" label="Full Name" width="200" sortable />
        <el-table-column prop="email" label="Email" min-width="180" />
        <el-table-column prop="mobile" label="Mobile" width="120" />
        <el-table-column prop="loginRole" label="Role" width="100">
          <template #default="{ row }">
            <el-tag :type="getRoleType(row.loginRole)" size="small">
              {{ row.loginRole }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="currentStatus" label="Status" width="90">
          <template #default="{ row }">
            <el-switch
              v-model="row.currentStatus"
              :active-value="1"
              :inactive-value="0"
              @change="toggleStatus(row)"
            />
          </template>
        </el-table-column>
        <el-table-column label="Actions" width="180" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="viewDetails(row)">
              View
            </el-button>
            <el-button type="primary" link @click="editUser(row)">
              Edit
            </el-button>
            <el-button type="danger" link @click="deleteUser(row)">
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- Pagination -->
      <div class="pagination-container">
        <el-pagination
          v-model:current-page="pagination.page"
          v-model:page-size="pagination.size"
          :total="pagination.total"
          :page-sizes="[10, 25, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handlePageChange"
        />
      </div>
    </el-card>

    <!-- View Details Dialog -->
    <el-dialog
      v-model="showDetailsDialog"
      title="User Details"
      width="600px"
    >
      <el-descriptions :column="2" border v-if="selectedUser">
        <el-descriptions-item label="Username">{{ selectedUser.username }}</el-descriptions-item>
        <el-descriptions-item label="Full Name">{{ selectedUser.fullName }}</el-descriptions-item>
        <el-descriptions-item label="Email">{{ selectedUser.email }}</el-descriptions-item>
        <el-descriptions-item label="Mobile">{{ selectedUser.mobile }}</el-descriptions-item>
        <el-descriptions-item label="Role">{{ selectedUser.loginRole }}</el-descriptions-item>
        <el-descriptions-item label="Status">
          <el-tag :type="selectedUser.currentStatus === 1 ? 'success' : 'danger'">
            {{ selectedUser.currentStatus === 1 ? 'Active' : 'Inactive' }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Address" :span="2">{{ selectedUser.address }}</el-descriptions-item>
        <el-descriptions-item label="City">{{ selectedUser.city }}</el-descriptions-item>
        <el-descriptions-item label="State">{{ selectedUser.state }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { sitemanagerApi } from '@/api'

const loading = ref(false)
const tableData = ref([])
const searchQuery = ref('')
const showDetailsDialog = ref(false)
const selectedUser = ref(null)
const selectedRows = ref([])

const pagination = reactive({
  page: 1,
  size: 10,
  total: 0
})

const getRoleType = (role) => {
  const types = {
    'ADMIN': 'danger',
    'SUPERADMIN': 'danger',
    'MANAGER': 'warning',
    'USER': 'info'
  }
  return types[role] || 'info'
}

const loadList = async () => {
  loading.value = true
  try {
    const response = await sitemanagerApi.getAll({
      page: pagination.page - 1,
      size: pagination.size,
      sort: 'adminId,desc'
    })
    
    const data = response.data
    tableData.value = (data.content || data).map(user => ({
      ...user,
      fullName: `${user.firstName || ''} ${user.middleName || ''} ${user.lastName || ''}`.trim()
    }))
    pagination.total = data.totalElements || data.length || 0
  } catch (error) {
    console.error('Failed to load list:', error)
    ElMessage.error('Failed to load site managers')
  } finally {
    loading.value = false
  }
}

const handleSearch = () => {
  pagination.page = 1
  loadList()
}

const resetSearch = () => {
  searchQuery.value = ''
  pagination.page = 1
  loadList()
}

const refreshList = () => {
  loadList()
  ElMessage.success('List refreshed')
}

const handleSelectionChange = (selection) => {
  selectedRows.value = selection
}

const handleSizeChange = () => {
  loadList()
}

const handlePageChange = () => {
  loadList()
}

const viewDetails = (row) => {
  selectedUser.value = row
  showDetailsDialog.value = true
}

const editUser = (row) => {
  window.location.href = `/sitemanager/${row.adminId}/edit`
}

const deleteUser = async (row) => {
  try {
    await ElMessageBox.confirm(
      `Are you sure you want to delete user "${row.username}"?`,
      'Confirm Delete',
      {
        confirmButtonText: 'Delete',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }
    )
    
    await sitemanagerApi.delete(row.adminId)
    ElMessage.success('User deleted successfully')
    loadList()
  } catch (error) {
    if (error !== 'cancel') {
      console.error('Delete failed:', error)
      ElMessage.error('Failed to delete user')
    }
  }
}

const toggleStatus = async (row) => {
  try {
    await sitemanagerApi.update(row.adminId, {
      ...row,
      currentStatus: row.currentStatus
    })
    ElMessage.success(`User ${row.currentStatus === 1 ? 'activated' : 'deactivated'}`)
  } catch (error) {
    console.error('Status update failed:', error)
    row.currentStatus = row.currentStatus === 1 ? 0 : 1
    ElMessage.error('Failed to update status')
  }
}

onMounted(() => {
  loadList()
})
</script>

<style lang="scss" scoped>
.sitemanager-list {
  .toolbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 16px;
    
    .search-box {
      display: flex;
      gap: 8px;
    }
    
    .actions {
      display: flex;
      gap: 8px;
    }
  }
  
  .pagination-container {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
