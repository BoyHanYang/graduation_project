import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Layout from '@/layout/Index.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [
            {
                path: '/dashboard',
                component: () => import('@/layout/dashboard/Index.vue'),
                name: 'dashboard',
                meta: {
                    title: '首页',
                    icon: '#icondashboard'
                }
            }
        ]
    },
    {
        path: "/system",
        component: Layout,
        name: "system",
        meta: {
            title: "系统管理",
            icon: "el-icon-menu",
            roles: ["sys:manage"]
        },
        children: [
            {
                path: "/userList",
                component: () => import('@/views/system/user/UserList.vue'),
                name: "userList",
                meta: {
                    title: "员工管理",
                    icon: "el-icon-s-custom",
                    roles: ["sys:user"]
                },
            },
            {
                path: "/roleList",
                component: () => import('@/views/system/role/RoleList.vue'),
                name: "roleList",
                meta: {
                    title: "角色管理",
                    icon: "el-icon-s-tools",
                    roles: ["sys:role"]
                },
            },
            {
                path: "/menuList",
                component: () => import('@/views/system/menu/MenuList.vue'),
                name: "menuList",
                meta: {
                    title: "权限管理",
                    icon: "el-icon-document",
                    roles: ["sys:menu"]
                },
            },
        ]
    },
    {
        path: "/memberRoot",
        component: Layout,
        name: "memberRoot",
        meta: {
            title: "会员管理",
            icon: "Setting",
            roles: ["sys:memberRoot"],
        },
        children: [
            {
                path: "/cardType",
                component: () => import('@/views/member/type/CardType.vue'),
                name: "cardType",
                meta: {
                    title: "会员卡类型",
                    icon: "UserFilled",
                    roles: ["sys:cardType"],
                },
            },
            {
                path: "/memberList",
                component: () => import('@/views/member/list/MemberList.vue'),
                name: "memberList",
                meta: {
                    title: "会员管理",
                    icon: "Wallet",
                    roles: ["sys:memberList"],
                },
            },
            {
                path: "/myFee",
                component: () => import('@/views/member/fee/MyFee.vue'),
                name: "myFee",
                meta: {
                    title: "我的充值",
                    icon: "Menu",
                    roles: ["sys:myFee"],
                },
            },
        ],
    },
    {
        path: "/courseRoot",
        component: Layout,
        name: "courseRoot",
        meta: {
            title: "课程管理",
            icon: "Setting",
            roles: ["sys:courseRoot"],
        },
        children: [
            {
                path: "/courseList",
                component: () => import('@/views/course/CourseList.vue'),
                name: "courseList",
                meta: {
                    title: "课程列表",
                    icon: "UserFilled",
                    roles: ["sys:courseList"],
                },
            },
            {
                path: "/myCourse",
                component: () => import('@/views/myCourse/MyCourse.vue'),
                name: "myCourse",
                meta: {
                    title: "我的课程",
                    icon: "Wallet",
                    roles: ["sys:myCourse"],
                },
            }
        ],
    },
    {
        path: "/materialRoot",
        component: Layout,
        name: "materialRoot",
        meta: {
            title: "器材管理",
            icon: "Setting",
            roles: ["sys:materialRoot"],
        },
        children: [
            {
                path: "/materialList",
                component: () => import('@/views/material/MaterialList.vue'),
                name: "materialList",
                meta: {
                    title: "器材列表",
                    icon: "UserFilled",
                    roles: ["sys:materialList"],
                },
            }
        ],
    },
    {
        path: "/goodsRoot",
        component: Layout,
        name: "goodsRoot",
        meta: {
            title: "商品管理",
            icon: "Setting",
            roles: ["sys:goodsRoot"],
        },
        children: [
            {
                path: "/goodsList",
                component: () => import('@/views/goods/GoodsList.vue'),
                name: "goodsList",
                meta: {
                    title: "商品列表",
                    icon: "UserFilled",
                    roles: ["sys:goodsList"],
                },
            },
            {
                path: "/orderList",
                component: () => import('@/views/order/orderList.vue'),
                name: "orderList",
                meta: {
                    title: "订单管理",
                    icon: "Wallet",
                    roles: ["sys:orderList"],
                },
            }
        ],
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router