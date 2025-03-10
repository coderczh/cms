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
        @select="getResource"
      >
        <el-sub-menu index="1" v-show="roleId === 1">
          <template #title>
            <el-icon><Setting /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item index="systemUser">用户管理</el-menu-item>
          <el-menu-item index="systemRole">角色管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <el-icon><Goods /></el-icon>
            <span>商品中心</span>
          </template>
          <el-menu-item index="productCategory">商品类别</el-menu-item>
          <el-menu-item index="productInfo">商品信息</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ROLE_INFO } from '@/global/constant'
import routes from '@/router'
import { localCache } from '@/utils/cache'

const roleId = localCache.getCache(ROLE_INFO).id

function getResource(index: string) {
  const textMap: Record<string, string> = {
    systemUser: '/main/system/user',
    systemRole: '/main/system/role',
    productCategory: '/main/product/category',
    productInfo: '/main/product/info'
  }
  Object.entries(textMap).forEach(([key, value]) => {
    if (index === key) {
      routes.push(value)
    }
  })
}

// const getResource = (index: string, indexPath: string[]) => {
//   console.log(index)

//   const textMap: Record<string, string> = {
//     systemUser: '用户管理',
//     systemRole: '角色管理',
//     productCategory: '用户管理',
//     productInfo: '角色管理'
//   }
//   console.log('菜单文本:', textMap[index])
// }
</script>

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
