<template>
  <div class="aside">
    <div class="logo">
      <img class="img" src="@/assets/img/logo.svg" alt="logo" />
      <h2 class="title">后台管理系统</h2>
    </div>
    <div class="content">
      <el-menu
        text-color="#b7bdc3"
        active-text-color="#fff"
        background-color="#001529"
      >
        <el-sub-menu index="1">
          <template #title>
            <!-- <el-icon>
              <component :is="item.icon.split('-icon-')[1]" />
            </el-icon> -->
            <el-icon><Setting /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item>用户管理</el-menu-item>
          <el-menu-item>角色管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <el-icon><Goods /></el-icon>
            <span>商品中心</span>
          </template>
          <el-menu-item>商品类别</el-menu-item>
          <el-menu-item>商品信息</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<script lang="ts" setup></script>

<style lang="less" scoped>
.aside {
  height: 100%;
  background-color: #001529;
}

.logo {
  display: flex;
  height: 28px;
  padding: 12px 10px 8px 10px;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  overflow: hidden;

  .img {
    height: 280%;
    margin: 0 10px;
  }

  .title {
    font-size: 20px;
    font-weight: 700;
    color: white;
    white-space: nowrap;
  }
}

.el-menu {
  border-right: none;
  user-select: none;
}

.el-sub-menu {
  .el-menu-item {
    padding-left: 50px !important;
    background-color: #0c2135;
  }

  .el-menu-item:hover {
    color: #fff;
  }

  .el-menu-item.is-active {
    background-color: #0a60bd;
  }
}
</style>
