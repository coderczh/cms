import { defineStore } from 'pinia'
import { accountLogin } from '@/service/login/login.ts'
import type { IAccountInfo } from '@/type/index.d.ts'
import { localCache } from '@/utils/cache'
import routes from '@/router'
import { LOGIN_TOKEN } from '@/global/constant'

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
    async loginAccountAction(accountInfo: IAccountInfo) {
      const accountLoginResult = await accountLogin(accountInfo)
      const message = accountLoginResult.data.message
      this.token = message.token
      this.userInfo = message.userInfo
      this.roleInfo = message.roleInfo
      localCache.setCache(LOGIN_TOKEN, this.token)

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

    loadLocalCache() {
      const token = localCache.getCache(LOGIN_TOKEN)
      const userInfo = localCache.getCache('userInfo')
      const roleInfo = localCache.getCache('userResource')
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
