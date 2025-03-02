import { defineStore } from 'pinia'
import { accountLogin, getMenuByRoleId } from '@/service/login/login.ts'
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
    token: localCache.getCache(LOGIN_TOKEN) ?? '',
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
      const menuResult = await getMenuByRoleId(this.roleInfo.id)
      console.log(menuResult)
      routes.push('/main')
    }
  }
})

export default useLoginStore
