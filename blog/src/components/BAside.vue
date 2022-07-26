<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        :default-active="$route.path.substr(1)"
        router
        class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <setting/>
          </el-icon>
          <span>系统管理</span>
        </template>
        <el-menu-item index="user">
          <el-icon>
            <Avatar/>
          </el-icon>
          <span>用户管理</span>
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item index="book">
        <span>书籍管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>


import {Location, Document, Menu as IconMenu, Setting, ArrowDown, Avatar} from '@element-plus/icons'

export default {
  name: "BAside",
  components: {Location, Document, Setting, IconMenu, ArrowDown, Avatar},
}
</script>

<style scoped>

</style>