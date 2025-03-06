import hyRequest from '../index.ts'
import type { IAccountInfo } from '@/type/index.d.ts'

export function accountLogin(accountInfo: IAccountInfo) {
  return hyRequest.instance({
    url: '/login/account',
    method: 'POST',
    data: accountInfo
  })
}
