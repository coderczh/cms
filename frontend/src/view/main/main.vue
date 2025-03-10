<template>
  <div class="main">
    <el-container class="main-container">
      <el-aside width="220px">
        <aside-info />
      </el-aside>
      <el-container>
        <el-header height="50px">
          <header-info />
        </el-header>
        <el-main>
          <!-- <router-view /> -->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import asideInfo from './aside/aside-info.vue'
import headerInfo from './header/header-info.vue'
</script>

<style lang="less" scoped>
.main {
  height: 100%;
}

.main-container {
  height: 100%;
}
</style>
