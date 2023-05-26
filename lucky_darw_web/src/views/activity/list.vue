<template>
  <div className="app-container">
    <!--活动页面-->

    <van-pull-refresh
      v-model="getDataLoading"
      success-text="刷新成功"
      @refresh="onRefresh"
    >
      <van-card
        :desc=" activity.describe "
        v-for="activity in activityList"
        :key="activity.id"
      >
        <template #tags>
          <div class="my-van-card-tags">
            <van-tag size="large"   type="warning" v-if="activity.status === 0">未开始</van-tag>
            <van-tag  size="large" type="primary" v-if="activity.status === 1">进行中</van-tag>
            <van-tag   size="large" type="danger" v-if="activity.status === 2">已结束</van-tag>
          </div>
        </template>
        <template #title>
          <div style="font-size: x-large;margin-bottom: 10px;">{{ activity.activityName }}</div>
        </template>
        <template #footer>
          <van-button size="mini" @click="toActivityInfo(activity.id)">详情</van-button>
        </template>
      </van-card>
    </van-pull-refresh>
  </div>
</template>

<script>
// import request from '@/utils/request'
import ActivityAPI from '../../api/activityAPI'

export default {
  data () {
    return {
      // 数据中心
      query: {
        pageIndex: 1,
        pageSize: 5
      },
      activityList: null,
      getDataLoading: false
    }
  },
  // 组件
  computed: {},
  // 页面渲染成功后获取数据
  created () {
    this.findData()
  },
  // 自定义方法
  methods: {

    findData () {
      ActivityAPI.list(this.query).then(res => {
        this.activityList = res.data.records
        this.query.pageIndex = res.data.current
        this.query.pageSize = res.data.size

        console.log(this.activityList)
      })
    },

    toActivityInfo (activityId) {
      console.log('activityId', activityId)
      this.$router.push({path: '/activity-info', query: {'activityId': activityId}})
    },

    onRefresh () {
      this.getDataLoading = true
      this.query.pageIndex = this.query.pageIndex + 1
      ActivityAPI.list(this.query).then(res => {
        if (res.data.records.length > 0) {
          res.data.records.forEach(item => this.activityList.push(item))
          this.query.pageIndex = res.data.current
          this.query.pageSize = res.data.size
        }
      }).finally(f => {
        this.getDataLoading = false
      })
    }

  }
}
</script>

<style scoped>
.my-van-card-tags{
  margin: 5px 0
}
</style>
