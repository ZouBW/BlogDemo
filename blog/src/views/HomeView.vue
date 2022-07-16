<template>
  <div style="padding: 10px">
    <!--    function-->
    <div style="margin: 10px 0">
      <el-button type="primary">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--search-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" clearable style="width: 20%"/>
      <el-button type="primary" style="margin-left: 5px;">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: auto">
      <el-table-column prop="date" label="日期" sortable/>
      <el-table-column prop="name" label="名称"/>
      <el-table-column prop="address" label="地址"/>
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default>
          <el-button link type="primary" @click="handleEdit">编辑</el-button>
          <el-popconfirm title="确认删除?">
            <template #reference>
              <el-button type="text">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5,10,20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 10,
      tableData: [
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ]
    }
  },
  methods: {
    handleEdit() {
      console.log('Edit');
    },
    handleSizeChange() {

    },
    handleCurrentChange() {

    },
  }
}
</script>
