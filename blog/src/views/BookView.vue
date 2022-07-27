<template>
  <div style="padding: 10px">
    <!--    function-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <!--      <el-button type="primary">导入</el-button>-->
      <!--      <el-button type="primary">导出</el-button>-->
    </div>
    <!--search-->
    <div style="margin: 10px 0">
      <el-input v-model="keyword" placeholder="请输入关键字" clearable style="width: 20%"/>
      <el-button type="primary" style="margin-left: 5px;" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: auto">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="name" label="名称"/>
      <el-table-column prop="price" label="售价"/>
      <el-table-column prop="author" label="作者"/>
      <el-table-column prop="createTime" label="出版日期"></el-table-column>
      <el-table-column label="封面">
        <template #default="scope">
          <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.cover"
              :preview-src-list="[scope.row.cover]"
              :preview-teleported="true"
              fit="cover"

          />
        </template>
      </el-table-column>
      <el-table-column label="操作">
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

      <el-dialog v-model="dialogVisible" title="提示" width="30%"
      >
        <el-form :model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="售价">
            <el-input v-model="form.price" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="form.author" style="width: 80%"/>
          </el-form-item>

          <el-form-item label="出版日期">
            <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width: 80%"
                            clearable></el-date-picker>
          </el-form-item>

          <el-form-item label="封面">
            <el-upload action="/api/files/upload"
                       :on-success="filesUploadSuccess" ref="upload">
              <el-button type="primary">点击上传</el-button>
            </el-upload
            >
          </el-form-item>
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save"
            >确 认</el-button>
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
  name: 'BookView',

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
    filesUploadSuccess(res) {
      console.log(res);
      this.form.cover = res.data;
    },
    save() {
      if (this.form.id) {
        request.put("/book", this.form).then(res => {
          console.log(res);
          if (res.code === '0') {
            this.$message.success("更新成功");
          } else {
            this.$message.error(res.msg)
          }
        });
      } else {
        request.post("/book", this.form).then(res => {
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
    load() {
      request.get("/book", {
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

    add() {
      this.dialogVisible = true;
      this.form = {}
      this.$refs['upload'].clearFiles()
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true;
      this.$nextTick(() => {
        this.$refs['upload'].clearFiles()
      })
    },
    handleSizeChange() {
      this.load()
    },
    handleCurrentChange() {
      this.load()
    },
    handleDelete(id) {
      console.log(id);
      request.delete("/book/" + id)
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
