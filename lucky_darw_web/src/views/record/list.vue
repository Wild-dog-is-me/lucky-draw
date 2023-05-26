<template>
  <div className="app-container">
    <van-pull-refresh
      v-model="getDataLoading"
      success-text="刷新成功"
      @refresh="onRefresh"
    >
      <van-card
        v-for="record in recordList"
        :key="record.id"
      >
        <template #tags>
          <div class="my-van-card-tags">
            <van-tag size="large"   type="danger" v-if="record.isWinning === 0">{{record.awardName}}</van-tag>
            <van-tag   size="large" v-if="record.isWinning === 1">{{record.awardName}}</van-tag>
          </div>
        </template>
        <template #title>
          <div style="font-size: x-large;margin-bottom: 10px;">{{ record.activityName }}</div>
        </template>
        <template #footer>
          <van-button size="mini"  v-if="record.isWinning === 1" @click="prizeType(record.id)">领奖</van-button>
        </template>
      </van-card>
    </van-pull-refresh>

  </div>
</template>

<script>
// import request from '@/utils/request'
import RecordAPI from '../../api/recordAPI'

export default {
  data () {
    return {
      // 数据中心
      query: {
        pageIndex: 1,
        pageSize: 5
      },
      recordList: null,
      getDataLoading: false
    }
  },
  // 组件
  computed: {},
  // 页面渲染成功后获取数据
  created () {
    this.findData()
  },
  mounted () {

  },
  // 自定义方法
  methods: {

    findData () {
      RecordAPI.list(this.query).then(res => {
        this.recordList = res.data.records
        this.query.pageIndex = res.data.current
        this.query.pageSize = res.data.size
        console.log(this.recordList)
      })
    },
    prizeType (recordId) {
      console.log(recordId)
    },
    onRefresh () {
      this.getDataLoading = true
      this.query.pageIndex = this.query.pageIndex + 1
      RecordAPI.list(this.query).then(res => {
        if (res.data.records.length > 0) {
          res.data.records.forEach(item => this.recordList.push(item))
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

</style>
