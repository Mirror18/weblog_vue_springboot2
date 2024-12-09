import { createApp } from 'vue' // 引入 createApp 方法
import App from '@/App.vue'     // 引入 App.vue 组件
// 导入路由
import router from '@/router'
//导入全局路由守卫
import '@/permission.js'

import './assets/main.css'      // 引入 main.css 样式文件
import 'animate.css';
// 导入 Element Plus 图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//导入 nprogress的css库
import 'nprogress/nprogress.css'
// 引入全局状态管理 Pinia
import pinia from "@/stores/index.js";



// 创建应用，并将 App 根组件挂载到 <div id="#app"></div> 中
const app = createApp(App)
// 引入图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
// 应用路由
app.use(router)
app.mount('#app')
// 应用 Pinia
app.use(pinia)
