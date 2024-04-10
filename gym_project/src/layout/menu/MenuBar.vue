<template>
  <menu-logo :isCollapsed="isCollapse"></menu-logo>
  <el-menu
      :collapse="isCollapse"
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
      unique-opened
      background-color="#304156"
      router
  >
    <menu-item :menuList="menuList"></menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import {ref, reactive, computed} from "vue";
import MenuItem from "@/layout/menu/MenuItem.vue";
import MenuLogo from "@/layout/menu/MenuLogo.vue";
import {useRoute} from "vue-router";
import {collapseStore} from "@/store/collapse/index";

const colstore = collapseStore();
const isCollapse = computed(() => {
  return colstore.getCollapse;
});
const route = useRoute();
//获取激活的菜单
const activeIndex = computed(() => {
  const {path} = route;
  return path;
});
let menuList = reactive([
  {
    path: "/dashboard",
    component: "Layout",
    name: "dashboard",
    meta: {
      title: "首页",
      icon: "HomeFilled",
      roles: ["sys:dashboard"],
    },
  },
  {
    path: "/system",
    component: "Layout",
    name: "system",
    meta: {
      title: "系统管理",
      icon: "Setting",
      roles: ["sys:manage"],
    },
    children: [

      {
        path: "/userList",
        component: "/system/user/UserList",
        name: "userList",
        meta: {
          title: "员工管理",
          icon: "UserFilled",
          roles: ["sys:user"],
        },
      },
      {
        path: "/roleList",
        component: "/system/role/RoleList",
        name: "roleList",
        meta: {
          title: "角色管理",
          icon: "Wallet",
          roles: ["sys:role"],
        },
      },
      {
        path: "/menuList",
        component: "/system/menu/MenuList",
        name: "menuList",
        meta: {
          title: "菜单管理",
          icon: "Menu",
          roles: ["sys:menu"],
        },
      },
    ],
  },
  {
    path: "/memberRoot",
    component: "Layout",
    name: "memberRoot",
    meta: {
      title: "会员管理",
      icon: "Setting",
      roles: ["sys:memberRoot"],
    },
    children: [
      {
        path: "/cardType",
        component: "/member/type/CardType",
        name: "cardType",
        meta: {
          title: "会员卡类型",
          icon: "UserFilled",
          roles: ["sys:cardType"],
        },
      },
      {
        path: "/memberList",
        component: "/member/list/MemberList",
        name: "memberList",
        meta: {
          title: "会员管理",
          icon: "Wallet",
          roles: ["sys:memberList"],
        },
      },
      {
        path: "/myFee",
        component: "/member/fee/FeeList",
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
    component: "Layout",
    name: "courseRoot",
    meta: {
      title: "课程管理",
      icon: "Setting",
      roles: ["sys:courseRoot"],
    },
    children: [
      {
        path: "/courseList",
        component: "/course/CourseList",
        name: "courseList",
        meta: {
          title: "课程列表",
          icon: "UserFilled",
          roles: ["sys:courseList"],
        },
      },
      {
        path: "/myCourse",
        component: "/course/myCourse",
        name: "myCourse",
        meta: {
          title: "我的课程",
          icon: "Wallet",
          roles: ["sys:myCourse"],
        },
      },
    ],
  },
  {
    path: "/materialRoot",
    component: "Layout",
    name: "materialRoot",
    meta: {
      title: "器材管理",
      icon: "Setting",
      roles: ["sys:materialRoot"],
    },
    children: [
      {
        path: "/materialList",
        component: "/material/materialList",
        name: "courseList",
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
    component: "Layout",
    name: "goodsRoot",
    meta: {
      title: "商品管理",
      icon: "Setting",
      roles: ["sys:goodsRoot"],
    },
    children: [
      {
        path: "/goodsList",
        component: "/goods/GoodsList",
        name: "goodsList",
        meta: {
          title: "商品列表",
          icon: "UserFilled",
          roles: ["sys:GoodsList"],
        },
      },
      {
        path: "/orderList",
        component: "/order/orderList",
        name: "orderList",
        meta: {
          title: "订单管理",
          icon: "Wallet",
          roles: ["sys:orderList"],
        },
      },
    ],
  },
]);
</script>

<style scoped lang="scss">

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 230px;
  min-height: 400px;
}

.el-menu {
  border-right: none;
}

:deep(.el-sub-menu .el-sub-menu__title) {
  color: #f4f4f5 !important;
}

:deep(.el-menu .el-menu-item) {
  color: #bfcbd9;
}

/* 菜单点中文字的颜色 */

:deep(.el-menu-item.is-active) {
  color: #409eff !important;
}

/* 当前打开菜单的所有子菜单颜色 */

:deep(.is-opened .el-menu-item) {
  background-color: #1f2d3d !important;
}

/* 鼠标移动菜单的颜色 */

:deep(.el-menu-item:hover) {
  background-color: #001528 !important;
}
</style>
