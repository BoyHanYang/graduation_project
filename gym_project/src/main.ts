import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import piniaPersist from 'pinia-plugin-persist'
//引入element plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//echarts
import * as echarts from 'echarts'
//图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//引入按钮权限
import { permission } from './directives/permission'
//引入Pinia构造函数
import { createPinia } from 'pinia'
// 实例化 Pinia
const pinia = createPinia()
//使用持久化插件
pinia.use(piniaPersist)
import zhCn from 'element-plus/es/locale/lang/zh-cn'
//信息确认框
import myconfirm from './utils/myconfirm'
import objCoppy from './utils/objCoppy'
import checkPermission from './directives/checkPermission'
const app = createApp(App);
app.use(router).use(ElementPlus, {
    locale: zhCn,
  }).use(pinia)
app.mount('#app')
app.directive('permission', permission)
//全局注册图标组件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
//全局挂载
app.config.globalProperties.$myconfirm = myconfirm
app.config.globalProperties.$objCoppy = objCoppy
app.config.globalProperties.$checkPermission = checkPermission
app.config.globalProperties.$echarts = echarts;
//权限验证
import './permission'