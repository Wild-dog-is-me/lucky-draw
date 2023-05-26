import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/user/login'
import Register from '@/views/user/register'
import Home from '@/views/home'
import About from '@/views/user/about'
import ActivityList from '@/views/activity/list'
import ActivityInfo from '@/views/activity/info'
import RecordList from '@/views/record/list'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      path: '/activity-list',
      name: 'activity-list',
      component: ActivityList
    },
    {
      path: '/activity-info',
      name: 'activity-info',
      component: ActivityInfo
    },
    {
      path: '/record-list',
      name: 'record-list',
      component: RecordList
    }
  ]
})
