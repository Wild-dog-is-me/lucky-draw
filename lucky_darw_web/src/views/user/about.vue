<template>
  <div className="app-container">
    <!--我的页面-->
    <van-card
      v-if="user"
    >
      <template #title>
        <div style="font-size: x-large;margin-bottom: 10px;">{{user.username}}</div>
      </template>
      <template #desc>
        <div style="margin: 5px 0">
          <div>
            名称：{{user.name}}
          </div>
          <div>
            电话：{{user.phone}}
          </div>
        </div>
      </template>
      <template #footer>
        <van-button size="mini"  @click=" () => {}">修改信息</van-button>
        <van-button size="mini"  @click=" () => {}">余额查看</van-button>
        <van-button size="mini"  @click="logOut">退出</van-button>
      </template>
    </van-card>
  </div>
</template>

<script>
// import request from '@/utils/request'
import UserAPI from '../../api/userAPI'
export default {
  data () {
    return {
      // 数据中心
      user: null
    }
  },
  // 组件
  computed: {},
  // 页面渲染成功后获取数据
  created () {
    this.me()
  },
  // 自定义方法
  methods: {

    me () {
      UserAPI.me().then(res => {
        if (res.code === 20000) {
          this.user = res.data
        }
      })
    },
    logOut () {
      this.$router.push({path: '/'})
    }
  }
}
</script>

<style scoped>

</style>
