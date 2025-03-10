import { defineStore } from 'pinia'
import {
  accountLogin,
  getResourceInfo,
  PhoneLogin
} from '@/service/login/login.ts'
import type { IAccountInfo, IPhoneInfo } from '@/type/index.d.ts'
import { localCache } from '@/utils/cache'
import routes from '@/router'
import { TOKEN, USER_INFO, ROLE_INFO } from '@/global/constant.ts'
// import { mainRouter } from '@/router/main'

interface ILoginState {
  token: string
  userInfo: any
  roleInfo: any
}

const useLoginStore = defineStore('login', {
  state: (): ILoginState => ({
    token: '',
    userInfo: {},
    roleInfo: {}
  }),
  actions: {
    async loginAccount(accountInfo: IAccountInfo) {
      // const accountLoginResult = await accountLogin(accountInfo)
      // const code = accountLoginResult.data.code
      // if (code !== 200) {
      //   return accountLoginResult.data.description
      // }
      // const message = accountLoginResult.data.message
      // this.token = message.token
      // this.userInfo = message.userInfo
      // this.roleInfo = message.roleInfo
      // localCache.setCache(TOKEN, this.token)
      // localCache.setCache(USER_INFO, this.token)
      // localCache.setCache(ROLE_INFO, this.token)

      // // 获取资源信息
      // const resourceResult = await getResourceInfo(this.roleInfo.id)
      // if (resourceResult.data.code != 200) {
      //   return resourceResult.data.description
      // }
      // const resource = resourceResult.data.message.resource
      // const files: Record<string, any> = import.meta.glob('@/router/main.ts', {
      //   eager: true
      // })
      // const module = files[Object.keys(files)[0]]
      // 先注册父路由

      // const routeObj1 = {
      //   path: '/main/test', // 这里用layout/test，/test, test都是一样的结果
      //   name: 'test',
      //   meta: { title: '测试路由test', noCache: true },
      //   component: () => import('@/views/error/404.vue')
      // }

      // const routeObj2 = {
      //   path: '/layout/test', // 这里用layout/test，/test, test都是一样的结果
      //   name: 'test',
      //   meta: { title: '测试路由test', noCache: true },
      //   component: () => import('@/views/error/404.vue')
      // }
      // const mainRouter = [
      //   {
      //     path: '/main/system/user',
      //     component: () => import('@/view/main/content/system/user/user.vue')
      //   },
      //   {
      //     path: '/main/system/role',
      //     component: () => import('@/view/main/content/system/role/role.vue')
      //   },
      //   {
      //     path: '/main/product/category',
      //     component: () =>
      //       import('@/view/main/content/product/category/category.vue')
      //   },
      //   {
      //     path: '/main/product/info',
      //     component: () => import('@/view/main/content/product/info/info.vue')
      //   }
      // ]

      // routes.addRoute('main', mainRouter[0])
      // routes.addRoute('main', mainRouter[1])
      // for (const item in module.mainRouter) {
      //   if (resource.includes(module.mainRouter[item].path)) {
      //     routes.addRoute('main', module.mainRouter[item])
      //     console.log(module.mainRouter[item])
      //   }
      // }
      // routes.clearRoutes()
      console.log(
        '当前路由表:',
        routes
          .getRoutes()
          .map((r) => ({ path: r.path, name: r.name, parent: r.parent?.name }))
      )
      routes.push('/main')
    },

    async loginPhone(phoneInfo: IPhoneInfo) {
      const phoneLoginResult = await PhoneLogin(phoneInfo)
      const code = phoneLoginResult.data.code
      if (code !== 200) {
        return phoneLoginResult.data.description
      }
      const message = phoneLoginResult.data.message
      this.token = message.token
      this.userInfo = message.userInfo
      this.roleInfo = message.roleInfo
      localCache.setCache(TOKEN, this.token)
      localCache.setCache(USER_INFO, this.token)
      localCache.setCache(ROLE_INFO, this.token)

      // const files: Record<string, any> = import.meta.glob('@/router/*.ts', {
      //   eager: true
      // })
      // for (const key in files) {
      //   const module = files[key]
      //   console.log(module.mainRouter)
      //   routes.addRoute('/main', module.mainRouter[0].path)
      // }

      routes.push('/main')
    },

    loadLoginInfo() {
      const token = localCache.getCache(TOKEN)
      const userInfo = localCache.getCache(USER_INFO)
      const roleInfo = localCache.getCache(ROLE_INFO)
      if (token && userInfo && roleInfo) {
        this.token = token
        this.userInfo = userInfo
        this.roleInfo = roleInfo

        // const files: Record<string, any> = import.meta.glob('@/router/*.ts', {
        //   eager: true
        // })
        // for (const key in files) {
        //   const module = files[key]
        //   console.log(module.mainRouter)
        //   routes.addRoute('/main', module.mainRouter[0].path)
        // }
      }
    }
  }
})

export default useLoginStore
