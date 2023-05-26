<template>
  <div class="app-container">
    <div style="text-align: center; padding: 20px 0px">
      人气抽奖
    </div>
    <van-form @submit="login">
      <van-field
        v-model="user.username"
        name="账户"
        label="账户"
        placeholder="账户"
        :rules="[{ required: true, message: '请填写账户' }]"
      />
      <van-field
        v-model="user.password"
        type="password"
        name="密码"
        label="密码"
        placeholder="密码"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">登入</van-button>
        <br>
        <router-link to="/register">
          <van-button round block type="info" >注册</van-button>
        </router-link>
      </div>
    </van-form>
  </div>
</template>

<script>
// import request from '@/utils/request'
import UserAPI from '../../api/userAPI'
import {Toast} from 'vant'
import VueCookie from 'vue-cookie'
export default {
  data () {
    return {
      // 数据中心
      user: {
        username: null,
        password: null
      }
    }
  },
  // 组件
  computed: {

  },
  // 页面渲染成功后获取数据
  created () {

  },
  // 自定义方法
  methods: {
    login (values) {
      console.log('submit', this.user)
      Toast.loading({
        message: '加载中...',
        forbidClick: true
      })
      UserAPI.login(this.user).then(response => {
        if (response.code === 20000) {
          Toast.success('登录成功！')
          VueCookie.set('Authorization', response.data, 2)
          this.$router.push({path: '/home'})
        } else {
          Toast.fail('登录失败！' + response.message)
        }
      }).finally(f => {
        Toast.clear()
      })
    }
  }
}
</script>

<style scoped>

</style>
