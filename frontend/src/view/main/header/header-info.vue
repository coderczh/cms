<template>
  <div class="header">
    <div class="content">
      <div class="info">
        <el-dropdown>
          <span class="el-dropdown-link">
            <el-avatar
              size="default"
              src="https://p3-passport.byteacctimg.com/img/user-avatar/ab74aba27eb2cba2b1dbd5fdb840e7bb~70x70.awebp"
            />
            <span class="name">coderczh</span>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>Action 1</el-dropdown-item>
              <el-dropdown-item>Action 2</el-dropdown-item>
              <el-dropdown-item>Action 3</el-dropdown-item>
              <el-dropdown-item disabled>Action 4</el-dropdown-item>
              <el-dropdown-item divided>Action 5</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup></script>

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
</style>
