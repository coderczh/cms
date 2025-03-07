import type { IRegistryInfo } from '@/type/registry'
import hyRequest from '..'

export function registry(registryInfo: IRegistryInfo) {
  return hyRequest.instance({
    url: '/registry',
    method: 'POST',
    data: registryInfo
  })
}
