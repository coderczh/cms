import hyRequest from '../index.ts'
import type { IAccountInfo, IPhoneInfo } from '@/type/index.d.ts'

export function accountLogin(accountInfo: IAccountInfo) {
  return hyRequest.instance({
    url: '/login/account',
    method: 'POST',
    data: accountInfo
  })
}

export function PhoneLogin(phoneInfo: IPhoneInfo) {
  return hyRequest.instance({
    url: '/login/phone',
    method: 'POST',
    data: phoneInfo
  })
}

export function getLoginCaptcha(phoneNo: string) {
  return hyRequest.instance({
    url: `/login/captcha/${phoneNo}`,
    method: 'GET'
  })
}
