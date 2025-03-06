import { defineStore } from 'pinia'
import { accountLogin } from '@/service/login/login.ts'
import type { IAccountInfo } from '@/type/index.d.ts'
import { localCache } from '@/utils/cache'
import routes from '@/router'
import { TOKEN, USER_INFO, ROLE_INFO } from '@/global/constant.ts'

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
      const accountLoginResult = await accountLogin(accountInfo)
      const code = accountLoginResult.data.code
      if (code !== 200) {
        return accountLoginResult.data.description
      }
      const message = accountLoginResult.data.message
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
