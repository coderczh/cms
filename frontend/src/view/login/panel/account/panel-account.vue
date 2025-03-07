<template>
  <div class="panel-account">
    <el-form
      :model="account"
      :rules="accountRules"
      label-width="60px"
      ref="formRef"
      status-icon
    >
      <el-form-item label="帐号" prop="accountNo">
        <el-input v-model="account.accountNo" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="account.password" show-password />
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import useLoginStore from '@/store/login/login.ts'
import type { FormRules, ElForm } from 'element-plus'
import type { IAccountInfo } from '@/type/index.d.ts'
import { localCache } from '@/utils/cache'

const account = reactive<IAccountInfo>({
  accountNo: localCache.getCache('accountNo') ?? '',
  password: localCache.getCache('password') ?? ''
})

const accountRules: FormRules = {
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
const loginStore = useLoginStore()
function loginAction(isRemPwd: boolean) {
  formRef.value?.validate((valid) => {
    if (valid) {
      loginStore
        .loginAccount({
          accountNo: account.accountNo,
          password: account.password
        })
        .then((res) => {
          if (res) {
            ElMessage({
              message: res,
              type: 'error',
              plain: true
            })
          } else if (isRemPwd) {
            localCache.setCache('accountNo', account.accountNo)
            localCache.setCache('password', account.password)
          } else {
            localCache.removeCache('accountNo')
            localCache.removeCache('password')
          }
        })
    }
  })
}

defineExpose({
  loginAction
})
</script>

<style lang="less" scoped>
.panel-account {
  color: red;
}
</style>
