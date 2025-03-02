import hyRequest from '..'
import type { IAccountInfo } from '@/type/index.d.ts'

export function accountLogin(accountInfo: IAccountInfo) {
  return hyRequest.instance({
    url: '/login',
    method: 'POST',
    data: accountInfo
  })
}

export function getMenuByRoleId(id: number) {
  return hyRequest.instance({
    url: `/role/${id}/menu`,
    method: 'GET'
  })
}
