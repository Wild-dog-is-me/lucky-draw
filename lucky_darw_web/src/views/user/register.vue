<template>
  <div className="app-container">
    <!--标签-->
    <van-nav-bar
      title="注册"
      left-text="返回"
      left-arrow
      @click-left="toLogin"
    />
    <van-form @submit="register">
      <van-field
        v-model="user.username"
        name="账号"
        label="账号"
        placeholder="账号"
        :rules="[{ required: true, message: '请填写账号' }]"
      />
      <van-field
        v-model="user.password"
        type="password"
        name="密码"
        label="密码"
        placeholder="密码"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
      <van-field
        v-model="user.name"
        name="用户名"
        label="用户名"
        placeholder="用户名"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="user.phone"
        name="电话"
        label="电话"
        placeholder="电话"
        :rules="[{ required: true, message: '请填写电话' }]"
      />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">注册</van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
// import request from '@/utils/request'
import UserAPI from '../../api/userAPI'
import {Toast} from 'vant'
export default {
  data () {
    return {
      // 数据中心
      user: {
        username: null,
        password: null,
        name: null,
        phone: null
      }
    }
  },
  // 组件
  computed: {},
  // 页面渲染成功后获取数据
  created () {

  },
  // 自定义方法
  methods: {
    register () {
      console.log('=========', this.user)
      Toast.loading({
        message: '加载中...',
        forbidClick: true
      })
      UserAPI.register(this.user).then(response => {
        console.log('response:', response)
        if (response.code === 20000) {
          Toast.success('注册成功！')
          this.$router.go(-1)
        } else {
          Toast.fail('注册失败！' + response.message)
        }
      }).finally(f => {
        Toast.clear()
      })
    },
    toLogin () {
      // this.$router.push({path: '/login'})
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>
