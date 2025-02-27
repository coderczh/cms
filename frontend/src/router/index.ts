import { LOGIN_TOKEN } from '@/global/constant'
import { localCache } from '@/utils/cache'
import { createRouter, createWebHashHistory } from 'vue-router'

const routes = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      redirect: '/main'
    },
    {
      path: '/login',
      component: () => import('@/view/login/login.vue')
    },
    {
      path: '/main',
      component: () => import('@/view/main/main.vue')
    },
    {
      path: '/:pathMatch(.*)*',
      component: () => import('@/view/404/404.vue')
    }
  ]
})

routes.beforeEach((to) => {
  const token = localCache.getCache(LOGIN_TOKEN)
  console.log(token)
  if (to.path === '/main' && !token) {
    return '/login'
  }
})

export default routes
