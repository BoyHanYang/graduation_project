<template>
  <el-breadcrumb separator="/" class="bread">
    <el-breadcrumb-item v-for="item in tabs">{{ item.meta.title }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup lang="ts">
import {useRoute, RouteLocationMatched} from 'vue-router'
import {Ref, watch, ref} from "vue";
// 获取当前的路由
const route = useRoute();
const tabs: Ref<RouteLocationMatched[]> = ref([])
const getBreadCrumb = () => {
  //找出当前路由的title
  let mached = route.matched.filter((item) => item.meta && item.meta.title);
  // 判断当前路由是否是首页，不是的话构造首页
  const first = mached[0];
  if (first.path !== '/dashboard') {
    // 构造首页，并赋值给match
    mached = [{path: "/dashboard", meta: {title: "首页"}} as any].concat(mached);
  }
  // 设置到面包屑导航数据内
  tabs.value = mached;
};
getBreadCrumb();
// 监听路由变化
watch(
    () => route.path,
    () => getBreadCrumb()
)
</script>

<style scoped>
:deep(.el-breadcrumb__inner) {
  color: #ffffff !important;
}

:deep(.el-breadcrumb__inner a) {
  color: #ffffff !important;
}

:deep(.el-breadcrumb__item) {
  font-size: 14px;
}

.bread {
  margin-left: 20px;
}
</style>