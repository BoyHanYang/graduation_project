import { createApp } from 'vue'
// import './style.css'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 信息确认框
import myconfirm from "@/utils/myconfirm.ts";
import objCoppy from "@/utils/objCoppy.ts";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
const app = createApp(App);
const pinia = createPinia()
app.use(router).use(ElementPlus, {
    locale: zhCn,
}).use(pinia)
app.mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
// 全局挂载
app.config.globalProperties.$myconfirm = myconfirm
app.config.globalProperties.$objCoppy = objCoppy