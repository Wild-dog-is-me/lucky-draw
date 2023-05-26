<template>
  <div className="app-container">
    <!--主页面-->
    <activity-list v-if="index === 0"/>
    <record-list v-if="index === 1"/>
    <about v-if="index === 2"/>
    <van-tabbar v-model="index">
      <van-tabbar-item icon="gift-o">活动</van-tabbar-item>
      <van-tabbar-item icon="orders-o">记录</van-tabbar-item>
      <van-tabbar-item icon="friends-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
// import request from '@/utils/request'
import About from '@/views/user/about'
import ActivityList from '@/views/activity/list'
import RecordList from '@/views/record/list'
export default {
  components: {
    ActivityList,
    RecordList,
    About
  },
  data () {
    return {
      // 数据中心
      index: 0
    }
  },
  // 组件
  computed: {},
  // 页面渲染成功后获取数据
  created () {

  },
  // 自定义方法
  methods: {}
}
</script>

<style scoped>

</style>
