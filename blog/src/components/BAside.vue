<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        default-active="user"
        default-openeds="['1']"
        class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <setting/>
          </el-icon>
          <span>系统管理</span>
        </template>
        <el-menu-item index="user" :route="{path: '/'}">
          <span>用户管理</span>
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item index="data" :route="{path: '/'}">
        <span>数据管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>


import {Location, Document, Menu as IconMenu, Setting, ArrowDown,} from '@element-plus/icons'

export default {
  name: "BAside",
  components: {Location, Document, Setting, IconMenu, ArrowDown,},
}
</script>

<style scoped>

</style>