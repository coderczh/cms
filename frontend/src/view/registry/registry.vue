<template>
  <div class="registry">
    <div class="registry-panel">
      <!-- 顶部标题 -->
      <h1 class="title">后台管理系统</h1>
      <div class="form">
        <el-form
          label-width="auto"
          style="max-width: 600px"
          :model="registryInfo"
          :rules="registryRules"
          ref="formRef"
          status-icon
        >
          <el-form-item label="手机号" required prop="phoneNo">
            <el-input v-model="registryInfo.phoneNo" />
          </el-form-item>
          <el-form-item label="账号" required prop="accountNo">
            <el-input v-model="registryInfo.accountNo" />
          </el-form-item>
          <el-form-item label="密码" required prop="password">
            <el-input v-model="registryInfo.password" show-password />
          </el-form-item>
        </el-form>
      </div>
      <!-- 底部区域 -->
      <el-button
        class="login-btn"
        type="primary"
        size="large"
        @click="registryBtn"
        >立即注册</el-button
      >
    </div>
  </div>
</template>

<script lang="ts" setup>
import routes from '@/router'
import { registry } from '@/service/registry/registry'
import type { IRegistryInfo } from '@/type/registry'
import type { ElForm, FormRules } from 'element-plus'
import { reactive, ref } from 'vue'

const registryInfo = reactive<IRegistryInfo>({
  phoneNo: '',
  accountNo: '',
  password: ''
})

const registryRules: FormRules = {
  phoneNo: [
    {
      required: true,
      message: '手机号不能为空',
      trigger: 'blur'
    },
    {
      pattern: /^1[3-9]\d{9}$/,
      message: '手机号码格式错误',
      trigger: 'blur'
    }
  ],
  accountNo: [
    {
      required: true,
      message: '账号不能为空',
      trigger: 'blur'
    },
    {
      pattern: /^[a-z0-9]{6,20}$/,
      message: '必须是6-20位字母或数字',
      trigger: 'blur'
    }
  ],
  password: [
    {
      required: true,
      message: '密码不能为空',
      trigger: 'blur'
    },
    {
      min: 6,
      max: 20,
      message: '必须是6-20位字母或数字',
      trigger: 'blur'
    }
  ]
}

const formRef = ref<InstanceType<typeof ElForm>>()
function registryBtn() {
  formRef.value?.validate((valid) => {
    if (valid) {
      registry(registryInfo).then((res) => {
        const registryResult = res.data
        if (registryResult.code !== 200) {
          ElMessage({
            message: registryResult.description,
            type: 'error',
            plain: true
          })
        } else {
          ElMessage({
            message: `${registryResult.description}, 请登录`,
            type: 'success',
            plain: true
          })
          routes.push('/login')
        }
      })
    }
  })
}
</script>

<style lang="less" scoped>
.registry {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: url(@/assets/img/login-bg.svg);
}

.registry-panel {
  width: 330px;
  margin-bottom: 150px;

  .title {
    text-align: center;
    margin-bottom: 15px;
  }

  .form {
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 30px;
    padding-bottom: 10px;
    margin-bottom: 20px;
    border: 1px solid #e8e8e8; /* 简写属性 */
  }

  .login-btn {
    margin-top: 10px;
    width: 100%;
  }
}
</style>
