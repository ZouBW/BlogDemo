<template>
  <div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px">请登陆</div>

      <el-form :model="form" size="normal" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input v-model="form.username" prefix-icon="Avatar" clearable/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" prefix-icon="Lock" show-password/>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登 陆</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script>
import {Search, Avatar, Lock} from '@element-plus/icons-vue'
import request from "@/utils/request";

export default {
  name: "LoginView",
  components: {
    Search, Avatar, Lock
  },
  data() {

    return {
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: 'blur'}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'}
        ]
      },
      form: {}
    }
  },
  created() {
    sessionStorage.removeItem("user")
  },
  methods: {
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          request.post("/user/login", this.form).then(
              res => {
                if (res.code === '0') {
                  this.$message.success("登陆成功")
                  sessionStorage.setItem("user", JSON.stringify(res.data))
                  this.$router.push("/")
                } else {
                  this.$message.error(res.msg)
                }
              }
          )
        }

      })

    },
  }
}
</script>

<style scoped>

</style>