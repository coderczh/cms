<template>
  <div class="login-panel">
    <!-- 顶部标题 -->
    <h1 class="title">后台管理系统</h1>
    <div class="tabs">
      <el-tabs type="border-card" stretch v-model="activeName">
        <el-tab-pane label="帐号登录" name="account">
          <template #label>
            <div class="label">
              <el-icon><User /></el-icon>
              <span>帐号登录</span>
            </div>
          </template>
          <panel-account ref="accountRef" />
        </el-tab-pane>
        <el-tab-pane label="手机登录" name="phone">
          <template #label>
            <div class="label">
              <el-icon><Iphone /></el-icon>
              <span>手机登录</span>
            </div>
          </template>
          <panel-phone ref="phoneRef" />
        </el-tab-pane>
      </el-tabs>
    </div>
    <!-- 底部区域 -->
    <div class="controls" v-show="showFooter">
      <el-checkbox v-model="isRemPwd" label="记住密码" />
      <el-link type="primary" @click="registry">注册</el-link>
    </div>
    <el-button class="login-btn" type="primary" size="large" @click="loginBtn"
      >立即登录</el-button
    >
  </div>
</template>

<script lang="ts" setup>
import { ref, watch } from 'vue'
import PanelAccount from './account/panel-account.vue'
import PanelPhone from './phone/panel-phone.vue'
import { localCache } from '@/utils/cache'
import routes from '@/router'

const isRemPwd = ref<boolean>(localCache.getCache('isRemPwd') ?? false)
watch(isRemPwd, (val) => {
  localCache.setCache('isRemPwd', val)
})

const activeName = ref('account')
const accountRef = ref<InstanceType<typeof PanelAccount>>()
const phoneRef = ref<InstanceType<typeof PanelPhone>>()
function loginBtn() {
  if (activeName.value === 'account') {
    accountRef.value?.loginAction(isRemPwd.value)
  } else {
    phoneRef.value?.loginAction()
  }
}

function registry() {
  routes.push('/registry')
}

const showFooter = ref<boolean>(true)
watch(activeName, (val) => {
  showFooter.value = val === 'account'
})
</script>

<style lang="less" scoped>
.login-panel {
  width: 330px;
  margin-bottom: 150px;

  .title {
    text-align: center;
    margin-bottom: 15px;
  }

  .label {
    display: flex;
    align-items: center;
    justify-content: center;

    .text {
      margin-left: 5px;
    }
  }

  .controls {
    margin-top: 12px;
    display: flex;

    justify-content: space-between;
  }

  .login-btn {
    margin-top: 10px;
    width: 100%;
  }
}
</style>
