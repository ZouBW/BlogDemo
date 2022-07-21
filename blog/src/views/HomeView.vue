<template>
  <div style="padding: 10px">
    <!--    function-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--search-->
    <div style="margin: 10px 0">
      <el-input v-model="keyword" placeholder="请输入关键字" clearable style="width: 20%"/>
      <el-button type="primary" style="margin-left: 5px;" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: auto">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="nickName" label="昵称"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="gender" label="性别"/>
      <el-table-column prop="address" label="地址"/>

      <el-table-column label="Operations">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" size="mini">编辑</el-button>
          <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
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

      <el-dialog v-model="dialogVisible" title="Tips" width="30%"
      >
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%"/>
          </el-form-item>

          <el-form-item label="性别">
            <el-radio-group v-model="form.gender">
              <el-radio label="男"/>
              <el-radio label="女"/>
              <el-radio label="未知"/>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 80%"/>
          </el-form-item>
        </el-form>

        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save"
        >确认</el-button
        >
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

import request from "@/utils/request";

export default {
  name: 'HomeView',

  data() {
    return {
      keyword: "",
      form: {},
      dialogVisible: false,
      currentPage: 1,
      pageSize: 10,
      total: 10,
      tableData: []
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("/api/user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          keyword: this.keyword
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    save() {
      if (this.form.id) {
        request.put("/api/user", this.form).then(res => {
          console.log(res);
          if (res.code === '0') {
            this.$message.success("更新成功");
          } else {
            this.$message.error(res.msg)
          }
        });
      } else {
        request.post("/api/user", this.form).then(res => {
          console.log(res);
          if (res.code === '0') {
            this.$message.success("新增成功");
          } else {
            this.$message.error(res.msg);
          }
        });
      }

      this.dialogVisible = false;
      this.load()

    },
    add() {
      this.dialogVisible = true;
      this.form = {}
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true;
    },
    handleSizeChange() {
      this.load()
    },
    handleCurrentChange() {
      this.load()
    },
    handleDelete(id) {
      console.log(id);
      request.delete("/api/user/" + id)
          .then(res => {
            if (this.res.code === '0') {
              this.$message.success("删除成功")
            } else {
              this.$message.error(res.msg);
            }

          })
      this.load()
    },
  }
}
</script>
