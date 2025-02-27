import { defineStore } from 'pinia'
import {
  accountLogin,
  getMenuByRoleId,
  getUserInfoById
} from '@/service/login/login.ts'
import type { IAccount } from '@/type/index.d.ts'
import { localCache } from '@/utils/cache'
import routes from '@/router'
import { LOGIN_TOKEN } from '@/global/constant'

interface ILoginState {
  token: string
  userInfo: any
}

const useLoginStore = defineStore('login', {
  state: (): ILoginState => ({
    token: localCache.getCache(LOGIN_TOKEN) ?? '',
    userInfo: {}
  }),
  actions: {
    async loginAccountAction(account: IAccount) {
      const loginResult = await accountLogin(account)
      const id = loginResult.data.content.id
      this.token = loginResult.data.content.token
      localCache.setCache(LOGIN_TOKEN, this.token)
      const userInfoResult = await getUserInfoById(id)
      this.userInfo = userInfoResult.data.content
      const menuResult = await getMenuByRoleId(this.userInfo.role.id)
      console.log(menuResult)

      routes.push('/main')
    }
  }
})

export default useLoginStore
