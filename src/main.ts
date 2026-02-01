import { createApp } from 'vue'
// main.ts
import 'virtual:uno.css'
import App from './App.vue'
import { useRouter } from '@/router'
import { useStore } from './stores'
import { useAssets } from './plugins/assets'
import './plugins/axios'

const app = createApp(App)
useRouter(app)
useStore(app)
useAssets()
app.mount('#app')
