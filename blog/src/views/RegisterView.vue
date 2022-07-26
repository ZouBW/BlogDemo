<template>
  <div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px">用户注册</div>

      <el-form :model="form" size="normal" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input v-model="form.username" prefix-icon="Avatar" clearable/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" prefix-icon="Lock" show-password/>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input v-model="form.confirm" type="password" prefix-icon="Lock" show-password/>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">注 册</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script>
import {Search, Avatar, Lock} from '@element-plus/icons-vue'
import request from "@/utils/request";

export default {
  name: "RegisterView",
  components: {
    Search, Avatar, Lock
  },
  data() {
    var confirmValidator = (rule, value, callback) => {
      if (value == "") {
        callback(new Error("请重新输入密码"))
      } else if (value !== this.form.password) {
        callback(new Error("两次密码输入不一致！"))
      } else {
        callback()
      }
    }
    return {
      form: {},
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: 'blur'},
          {min: 3, max: 5, message: "命名长度为3至5字符"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'}
        ],
        confirm: [
          {required: true, validator: confirmValidator, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    register() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          request.post("/user/register", this.form).then(
              res => {
                if (res.code === '0') {
                  this.$message.success("注册成功")
                  this.$router.push("/login")
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