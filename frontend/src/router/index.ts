import { TOKEN } from '@/global/constant.ts'
import { localCache } from '@/utils/cache.ts'
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
      path: '/registry',
      component: () => import('@/view/registry/registry.vue')
    },
    {
      path: '/main',
      component: () => import('@/view/main/main.vue'),
      children: [
        {
          path: 'system/user',
          component: () => import('@/view/main/content/system/user/user.vue')
        },
        {
          path: 'system/role',
          component: () => import('@/view/main/content/system/role/role.vue')
        },
        {
          path: 'product/category',
          component: () =>
            import('@/view/main/content/product/category/category.vue')
        },
        {
          path: 'product/info',
          component: () => import('@/view/main/content/product/info/info.vue')
        }
      ]
    },
    {
      path: '/:pathMatch(.*)*',
      component: () => import('@/view/404/404.vue')
    }
  ]
})

routes.beforeEach((to) => {
  const token = localCache.getCache(TOKEN)
  if (to.path === '/main' && !token) {
    return '/login'
  }
})

export default routes
