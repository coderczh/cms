<template>
  <div class="header">
    <div class="content">
      <div class="info">
        <el-dropdown>
          <span class="user-info">
            <el-avatar
              size="default"
              src="https://p3-passport.byteacctimg.com/img/user-avatar/ab74aba27eb2cba2b1dbd5fdb840e7bb~70x70.awebp"
            />
            <span class="name">coderczh</span>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="logout">
                <el-icon><Close /></el-icon>
                <span>退出登录</span>
              </el-dropdown-item>
              <el-dropdown-item divided>
                <el-icon><InfoFilled /></el-icon>
                <span>个人信息</span>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-icon><Unlock /></el-icon>
                <span>修改密码</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { LOGIN_TOKEN } from '@/global/constant'
import { localCache } from '@/utils/cache'
import { useRouter } from 'vue-router'

const router = useRouter()
function logout() {
  localCache.removeCache(LOGIN_TOKEN)
  router.push('/login')
}
</script>

<style lang="less" scoped>
.header {
  display: flex;
  align-items: center;
  flex: 1;
  height: 100%;

  .content {
    display: flex;
    justify-content: right;
    align-items: center;
    flex: 1;
    padding: 0 18px;
  }
}

.info {
  .user-info {
    display: flex;
    align-items: center;
    cursor: pointer;

    .name {
      margin-left: 5px;
    }
  }
}

.el-tooltip__trigger:focus-visible {
  outline: unset !important;
}
</style>
