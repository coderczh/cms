import { createPinia } from 'pinia'
import useLoginStore from './login/login'
import type { App } from 'vue'

const pinia = createPinia()

export default function registryStore(app: App<Element>) {
  app.use(pinia)
  const loginStore = useLoginStore()
  loginStore.loadLocalCache()
}
