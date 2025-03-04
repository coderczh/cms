<template>
  <div class="main">
    <el-container class="main-container">
      <el-aside width="240px">
        <aside-menu />
      </el-aside>
      <el-container>
        <el-header height="50px">Header</el-header>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import asideMenu from './aside/aside-menu.vue'
</script>

<style lang="less" scoped>
.main {
  height: 100%;
  color: red;
}

.main-container {
  height: 100%;
}
</style>
