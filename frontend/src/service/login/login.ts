import hyRequest from '..'
import type { IAccount } from '@/type/index.d.ts'

export function accountLogin(account: IAccount) {
  return hyRequest.instance({
    url: '/login',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    data: {
      loginInfo: account
    }
  })
}

export function getUserInfoById(id: number) {
  return hyRequest.instance({
    url: `/user/${id}`,
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function getMenuByRoleId(id: number) {
  return hyRequest.instance({
    url: `/role/${id}/menu`,
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
