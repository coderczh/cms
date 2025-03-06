<template>
  <div class="panel-phone">
    <el-form label-width="70px" :model="phone" :rules="phoneRules" status-icon>
      <el-form-item label="手机号" prop="phoneNo">
        <el-input v-model="phone.phoneNo" />
      </el-form-item>
      <el-form-item label="验证码" prop="captcha">
        <div class="verify-code">
          <el-input v-model="phone.captcha" />
          <el-button class="get-btn" type="primary" @click="getCaptcha"
            >获取验证码</el-button
          >
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import type { IPhoneInfo } from '@/type'
import type { FormRules } from 'element-plus'
import { reactive } from 'vue'
import { ElMessage } from 'element-plus'

const phone = reactive<IPhoneInfo>({
  phoneNo: '',
  captcha: ''
})

const phoneRules: FormRules = {
  phoneNo: [
    {
      required: true,
      message: '手机号不能为空',
      trigger: 'blur'
    },
    {
      pattern: /^1[3-9]\d{9}$/,
      message: '手机号码错误',
      trigger: 'blur'
    }
  ],
  captcha: [
    {
      required: true,
      message: '验证码不能为空',
      trigger: 'blur'
    },
    {
      min: 6,
      max: 6,
      message: '验证码错误',
      trigger: 'blur'
    }
  ]
}

function getCaptcha() {
  const captcha = Math.random().toString().slice(2, 8).padEnd(6, '0')
  ElMessage({
    message: `您的验证码是：${captcha}`,
    type: 'success',
    plain: true
  })
}
</script>

<style lang="less" scoped>
.panel-phone {
  color: red;
}

.verify-code {
  display: flex;

  .get-btn {
    margin-left: 8px;
  }
}
</style>
