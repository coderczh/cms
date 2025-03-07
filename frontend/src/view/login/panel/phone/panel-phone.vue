<template>
  <div class="panel-phone">
    <el-form
      label-width="70px"
      :model="phone"
      :rules="phoneRules"
      ref="formRef"
      status-icon
    >
      <el-form-item label="手机号" prop="phoneNo">
        <el-input v-model="phone.phoneNo" />
      </el-form-item>
      <el-form-item label="验证码" prop="captcha">
        <div class="verify-code">
          <el-input v-model="phone.captcha" />
          <el-button
            class="get-btn"
            type="primary"
            :disabled="getBtnDisabled"
            @click="getCaptcha"
            >获取验证码&nbsp;
            <span v-show="timeShow">{{ time }}</span></el-button
          >
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import type { IPhoneInfo } from '@/type'
import type { ElForm, FormRules } from 'element-plus'
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import useLoginStore from '@/store/login/login'
import { getLoginCaptcha } from '@/service/login/login'

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
      message: '手机号码格式错误',
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
      message: '验证码长度错误',
      trigger: 'blur'
    }
  ]
}

/**
 * 获取手机验证码
 */
const getBtnDisabled = ref<boolean>(false)
const time = ref<number>(60)
const timeShow = ref<boolean>(false)
async function getCaptcha() {
  if (phone.phoneNo && /^1[3-9]\d{9}$/.test(phone.phoneNo)) {
    getBtnDisabled.value = true
    timeShow.value = true
    const timerId = setInterval(() => {
      time.value--
      if (time.value <= 0) {
        getBtnDisabled.value = false
        timeShow.value = false
        time.value = 60
        clearInterval(timerId)
      }
    }, 1000)
    const captchaResult = await getLoginCaptcha(phone.phoneNo)
    if (captchaResult.data.code !== 200) {
      ElMessage({
        message: captchaResult.data.description,
        type: 'error',
        plain: true
      })
    } else {
      ElMessage({
        message: `您的验证码是：${captchaResult.data.message}`,
        type: 'success',
        plain: true
      })
    }
  } else {
    ElMessage({
      message: '手机号码错误',
      type: 'error',
      plain: true
    })
  }
}

const formRef = ref<InstanceType<typeof ElForm>>()
const loginStore = useLoginStore()
function loginAction() {
  formRef.value?.validate((valid) => {
    if (valid) {
      loginStore
        .loginPhone({
          phoneNo: phone.phoneNo,
          captcha: phone.captcha
        })
        .then((res) => {
          if (res) {
            ElMessage({
              message: res,
              type: 'error',
              plain: true
            })
          } else {
            ElMessage({
              message: '登录成功',
              type: 'success',
              plain: true
            })
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
