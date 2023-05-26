<template>
  <div class="app-container">
    <!--标签-->
    <van-nav-bar
      title="活动详情"
      left-text="返回"
      left-arrow
      @click-left="toActivityList"
    />

    <van-card v-if="activityInfo.activityVO">
      <template #title>
        <div style="font-size: x-large;margin-bottom: 10px;">{{ activityInfo.activityVO.activityName }}</div>
      </template>
      <template #desc>
        <div style="margin: 5px 0">
          <div>
            描述：{{activityInfo.activityVO.describe}}
          </div>
          <div>
            活动时间：{{ activityInfo.activityVO.startTime }} ~ {{ activityInfo.activityVO.updateTime }}
          </div>
        </div>
      </template>
      <template #tags>
        <div style="margin: 5px 0">
          <van-tag   type="warning" v-if="activityInfo.activityVO.status === 0">未开始</van-tag>
          <van-tag   type="primary" v-if="activityInfo.activityVO.status === 1">进行中</van-tag>
          <van-tag   type="danger" v-if="activityInfo.activityVO.status === 2">已结束</van-tag>
        </div>
      </template>
      <template #footer>
        <van-button size="mini" v-if="activityInfo.activityVO.status === 1" @click="draw(activityInfo.activityVO.id)">参与</van-button>
      </template>
    </van-card>

    <div style="text-align: center; font-size: inherit; margin: 15px 0">
      奖项列表
    </div>

    <div v-if="activityInfo.awardVOList">
      <van-card v-for="award in activityInfo.awardVOList" :key="award.id">
        <template #title>
          <div style="font-size: large;margin-bottom: 10px;">{{award.awardName}}</div>
        </template>
        <template #desc>
          <div style="margin: 5px 0">
            <div>
              奖品：{{award.prizeName}}
            </div>
          </div>
        </template>
      </van-card>
    </div>

  </div>
</template>

<script>
// import request from '@/utils/request'
import ActivityAPI from '../../api/activityAPI'
import {Toast} from 'vant'

export default {
  data () {
    return {
      // 数据中心
      activityInfo: {}
    }
  },
  // 组件
  computed: {

  },
  // 页面渲染成功后获取数据
  created () {
    this.one(this.$route.query.activityId)
  },
  // 自定义方法
  methods: {

    one (activityId) {
      console.log('活动详情id', activityId)
      ActivityAPI.one({'id': activityId}).then(res => {
        this.activityInfo = res.data
        console.log('activityInfo:', this.activityInfo)
      })
    },

    toActivityList () {
      this.$router.go(-1)
    },
    draw (activityId) {
      console.log('this.activity', activityId)
      let drawResult = false
      let draw = null

      ActivityAPI.draw({'activityId': activityId}).then(res => {
        console.log('res', res)
        draw = res.data
        console.log('draw', draw)
      }).finally(f => {
        drawResult = true
      })

      const toast = Toast.loading({
        duration: 0, // 持续展示 toast
        forbidClick: true,
        message: '抽奖进行中 - 10'
      })

      let second = 10
      const timer = setInterval(() => {
        second--
        if (second) {
          toast.message = `抽奖进行中 - ${second}`
        } else {
          if (drawResult) {
            clearInterval(timer)
            Toast.success(draw.awardName)
            // 手动清除 Toast
            Toast.clear()
          }
        }
      }, 1000)
    }
  }
}
</script>

<style scoped>

</style>
