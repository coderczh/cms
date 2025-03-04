import hyRequest from '..'
import type { IAccountInfo } from '@/type/index.d.ts'

export function accountLogin(accountInfo: IAccountInfo) {
  return hyRequest.instance({
    url: '/login',
    method: 'POST',
    data: accountInfo
  })
}
