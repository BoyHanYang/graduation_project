import router from "./router";
import { userStore } from "./store/user";
import { menuStore } from "./store/menu";
//白名单 不需要权限可以访问
const whiteList = ['/login']
//全局守卫路由
router.beforeEach(async (to, from, next) => {
    //获取用户的store
    const ustore = userStore()
    //获取菜单的store
    const mstore = menuStore()
    //获取token
    const token = ustore.getToken
    //判断token是否存在
    if (token) { //存在
        if (to.path === '/login' || to.path == '/') {
            next({ path: '/' })
        } else {
            const menuList = mstore.getMenuList
            if (menuList.length > 0) {
                next()
            } else {
                try {
                    //获取用户信息
                    await ustore.getInfo()
                    //获取菜单信息,动态生成路由
                    await mstore.getMenu(router, { userId: ustore.getUserId, userType: ustore.getUserTyp })
                    //等待路由全部挂载
                    next({ ...to, replace: true })
                } catch (error) {
                    localStorage.clear()
                    next({ path: '/login' })
                }
            }
        }
    } else { //不存在
        if (whiteList.indexOf(to.path) !== -1) { //存在白名单，直接放行
            next()
        } else {
            //去登录
            next({ path: '/login' })
        }
    }
})