import { defineStore } from "pinia";
import { getMenuListApi } from '@/api/login/index.ts'
import { RouteRecordRaw } from "vue-router";
import Layout from '@/layout/Index.vue'
import {InfoParm} from "@/api/login/LoginModel.ts";
import Center from "@/layout/center/center.vue";
// 获取vies的所有页面
const modules = import.meta.glob('../../views/**/*.vue')
// 定义store
export const menuStore = defineStore('menuStore',{
    state:()=>{
        return {
            menuList: [
                {
                    path: "/dashboard",
                    component: "Layout",
                    name: "dashboard",
                    meta: {
                        title: "首页",
                        icon: "HomeFilled",
                        roles: ["sys:dashboard"],
                    },
                    children: []
                } as any
            ]
        }
    },
    getters:{
        getMenuList: (state) => {
            return state.menuList
        }
    },
    actions: {
        getMenu(router: any,parm:InfoParm) {
            return new Promise((resolve, reject) => {
                getMenuListApi(parm).then((res) => {
                    let accessRoute;
                    if (res && res.code == 200) {
                        //动态生成路由
                        accessRoute = generateRoutes(res.data, router)
                        const desk = [
                            {
                                path: "/dashboard",
                                component: "Layout",
                                name: "dashboard",
                                meta: {
                                    title: "首页",
                                    icon: "HomeFilled",
                                    roles: ["sys:dashboard"],
                                },
                                children: []
                            }
                        ] as any
                        accessRoute = generateRoutes(res.data, router)
                        this.menuList = this.menuList.concat(accessRoute)
                    }
                    resolve(this.menuList)
                }).catch((error)=>{
                    reject(error)
                })
            })
        }
    },
    /*persist: {
        enabled: true, //开启持久化操作,默认全部字段存储，存储在sessionSotrage里面
        strategies: [
            { storage: localStorage, paths: ['menuList'] },
        ],
    }*/
})
// 动态生成路由
export function generateRoutes(routes: RouteRecordRaw[], router: any) {
    //定义接收生成的菜单
    const res: Array<RouteRecordRaw> = [];
    routes.forEach((route: any) => {
        //把route里面数据放到新的tmp里面
        const tmp = { ...route }
        const component = tmp.component;
        if (route.component) {
            if (component == 'Layout') {
                tmp.component = Layout;
            } else {
                tmp.component = modules[`../../views${component}.vue`]
            }
        }
        //如果存在下级
        if (tmp.children && tmp.children.length > 0) {
            if (route.component != 'Layout') {
                tmp.component = Center;
            }
            //递归调用
            tmp.children = generateRoutes(tmp.children, router)
        }
        //动态添加路由
        router.addRoute(tmp)
        res.push(tmp)
    })
    return res;
}