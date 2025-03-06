import { createApp } from 'vue'
import App from './App.vue'
import 'normalize.css'
import './assets/css/index.less'
import route from './router/index.ts'
import registerIcon from './global/register-icon.ts'
import registryStore from './store/index.ts'

createApp(App).use(registerIcon).use(registryStore).use(route).mount('#app')
