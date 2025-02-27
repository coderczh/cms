// service统一出口
import HYRequest from './request/index.ts'
import { BASE_URL, TIME_OUT } from './config/index.ts'
import { localCache } from '@/utils/cache.ts'
import { LOGIN_TOKEN } from '@/global/constant.ts'

const hyRequest = new HYRequest({
  baseURL: BASE_URL,
  timeout: TIME_OUT,
  interceptors: {
    requestInterceptor: (config) => {
      const token = localCache.getCache(LOGIN_TOKEN)
      if (config.headers && token) {
        config.headers.Authorization = `Bearer ${token}`
      }
      return config
    }
    //   requestInterceptorCatch: (err) => {
    //     console.log('请求失败的拦截')
    //     return err
    //   },
    //   responseInterceptor: (res) => {
    //     console.log('请求成功的拦截')
    //     return res
    //   },
    //   responseInterceptorCatch: (err) => {
    //     console.log('响应成功的拦截')
    //     return err
    //   }
  }
})

export default hyRequest
