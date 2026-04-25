<template>
  <div class="sitemanager-form">
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>{{ isEdit ? 'Edit' : 'Create' }} Site Manager</span>
          <el-button @click="$router.back()">Back</el-button>
        </div>
      </template>

      <el-form
        ref="formRef"
        :model="formData"
        :rules="formRules"
        label-width="140px"
        class="form-container"
      >
        <el-divider content-position="left">Personal Information</el-divider>
        
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="First Name" prop="firstName">
              <el-input v-model="formData.firstName" placeholder="Enter first name" />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Middle Name" prop="middleName">
              <el-input v-model="formData.middleName" placeholder="Enter middle name" />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Last Name" prop="lastName">
              <el-input v-model="formData.lastName" placeholder="Enter last name" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="Gender" prop="gender">
              <el-select v-model="formData.gender" placeholder="Select gender" style="width: 100%">
                <el-option label="Male" value="Male" />
                <el-option label="Female" value="Female" />
                <el-option label="Other" value="Other" />
              </el-select>
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Birth Date" prop="birthDate">
              <el-date-picker
                v-model="formData.birthDate"
                type="date"
                placeholder="Select birth date"
                style="width: 100%"
                value-format="YYYY-MM-DD"
              />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Marital Status" prop="maritalStatus">
              <el-select v-model="formData.maritalStatus" placeholder="Select status" style="width: 100%">
                <el-option label="Single" value="Single" />
                <el-option label="Married" value="Married" />
                <el-option label="Divorced" value="Divorced" />
                <el-option label="Widowed" value="Widowed" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="Employee Code" prop="empCode">
              <el-input v-model="formData.empCode" placeholder="Enter employee code" />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Joining Date" prop="joiningDate">
              <el-date-picker
                v-model="formData.joiningDate"
                type="date"
                placeholder="Select joining date"
                style="width: 100%"
                value-format="YYYY-MM-DD"
              />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Leaving Date" prop="leavingDate">
              <el-date-picker
                v-model="formData.leavingDate"
                type="date"
                placeholder="Select leaving date"
                style="width: 100%"
                value-format="YYYY-MM-DD"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="left">Contact Information</el-divider>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Email" prop="email">
              <el-input v-model="formData.email" placeholder="Enter email address" />
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="Mobile" prop="mobile">
              <el-input v-model="formData.mobile" placeholder="Enter mobile number" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Landline" prop="landline">
              <el-input v-model="formData.landline" placeholder="Enter landline number" />
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="PIN Code" prop="pin">
              <el-input v-model="formData.pin" placeholder="Enter PIN code" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="Address" prop="address">
          <el-input
            v-model="formData.address"
            type="textarea"
            :rows="3"
            placeholder="Enter full address"
          />
        </el-form-item>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="City" prop="city">
              <el-input v-model="formData.city" placeholder="Enter city" />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="State" prop="state">
              <el-input v-model="formData.state" placeholder="Enter state" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="left">Account Information</el-divider>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="Username" prop="username">
              <el-input v-model="formData.username" placeholder="Enter username" :disabled="isEdit" />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Password" prop="password" v-if="!isEdit">
              <el-input
                v-model="formData.password"
                type="password"
                show-password
                placeholder="Enter password"
              />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Login Role" prop="loginRole">
              <el-select v-model="formData.loginRole" placeholder="Select role" style="width: 100%">
                <el-option label="User" value="USER" />
                <el-option label="Manager" value="MANAGER" />
                <el-option label="Admin" value="ADMIN" />
                <el-option label="Super Admin" value="SUPERADMIN" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="Current Status" prop="currentStatus">
              <el-switch
                v-model="formData.currentStatus"
                :active-value="1"
                :inactive-value="0"
                active-text="Active"
                inactive-text="Inactive"
              />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Login Status" prop="loginStatus">
              <el-switch
                v-model="formData.loginStatus"
                :active-value="1"
                :inactive-value="0"
                active-text="Enabled"
                inactive-text="Disabled"
              />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="Multi Login" prop="multiLogin">
              <el-switch
                v-model="formData.multiLogin"
                :active-value="1"
                :inactive-value="0"
                active-text="Allowed"
                inactive-text="Not Allowed"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" :loading="submitting" @click="handleSubmit">
            {{ isEdit ? 'Update' : 'Create' }}
          </el-button>
          <el-button @click="$router.back()">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { sitemanagerApi } from '@/api'

const route = useRoute()
const router = useRouter()
const formRef = ref(null)
const submitting = ref(false)

const isEdit = computed(() => !!route.params.id)

const formData = reactive({
  adminId: null,
  firstName: '',
  middleName: '',
  lastName: '',
  gender: '',
  birthDate: '',
  maritalStatus: '',
  empCode: '',
  joiningDate: '',
  leavingDate: '',
  address: '',
  city: '',
  state: '',
  pin: '',
  landline: '',
  mobile: '',
  email: '',
  username: '',
  password: '',
  loginRole: 'USER',
  currentStatus: 1,
  loginStatus: 1,
  multiLogin: 0,
  menuType: 'default'
})

const formRules = {
  firstName: [{ required: true, message: 'First name is required', trigger: 'blur' }],
  lastName: [{ required: true, message: 'Last name is required', trigger: 'blur' }],
  email: [
    { required: true, message: 'Email is required', trigger: 'blur' },
    { type: 'email', message: 'Please enter valid email', trigger: 'blur' }
  ],
  mobile: [{ required: true, message: 'Mobile is required', trigger: 'blur' }],
  username: [{ required: true, message: 'Username is required', trigger: 'blur' }],
  password: !isEdit.value ? [{ required: true, message: 'Password is required', trigger: 'blur' }] : [],
  loginRole: [{ required: true, message: 'Role is required', trigger: 'change' }]
}

const loadUser = async () => {
  if (!isEdit.value) return
  
  try {
    const response = await sitemanagerApi.getById(route.params.id)
    const user = response.data
    
    Object.assign(formData, {
      ...user,
      birthDate: user.birthDate?.substring(0, 10),
      joiningDate: user.joiningDate?.substring(0, 10),
      leavingDate: user.leavingDate?.substring(0, 10)
    })
  } catch (error) {
    console.error('Failed to load user:', error)
    ElMessage.error('Failed to load user details')
  }
}

const handleSubmit = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (!valid) return
    
    submitting.value = true
    
    try {
      if (isEdit.value) {
        await sitemanagerApi.update(route.params.id, formData)
        ElMessage.success('User updated successfully')
      } else {
        await sitemanagerApi.create(formData)
        ElMessage.success('User created successfully')
      }
      
      router.push('/sitemanager')
    } catch (error) {
      console.error('Submit failed:', error)
      ElMessage.error(error.response?.data?.message || 'Operation failed')
    } finally {
      submitting.value = false
    }
  })
}

onMounted(() => {
  loadUser()
})
</script>

<style lang="scss" scoped>
.sitemanager-form {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .form-container {
    max-width: 1200px;
    margin: 0 auto;
  }
}
</style>
