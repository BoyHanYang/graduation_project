<template>
  <el-breadcrumb separator="/" class="bred">
    <el-breadcrumb-item v-for="item in tabs">{{item.meta.title}}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup lang="ts">
import { watch, ref, Ref } from "vue";
import { useRoute, RouteLocationMatched } from "vue-router";
//获取当前路由
const route = useRoute();
//面包屑导航数据  Ref :定义数据的类型
const tabs: Ref<RouteLocationMatched[]> = ref([]);
//监听到路由发生变化的操作
const getBredcrumb = () => {
  //找出存在 title的菜单
  let mached = route.matched.filter((item) => item.meta && item.meta.title);
  console.log(mached);
  //判断第一个是不是首页，不是构造一个首页
  const first = mached[0];
  console.log(first.path)
  if (first.path !== "/dashboard") {
    //构造首页，并放到mached里面
    mached = [{ path: "/dashboard", meta: { title: "首页" } } as any].concat(
      mached
    );
  }
  //设置到面包屑导航数据里面
  tabs.value = mached;
};
getBredcrumb()
//监听路由的变化
watch(
  () => route.path,
  () => getBredcrumb()
);
</script>

<style scoped>
:deep(.el-breadcrumb__inner) {
  color: #fff !important;
}
:deep(.el-breadcrumb__inner a) {
  color: #fff !important;
}
:deep(.el-breadcrumb__item) {
  font-size: 14px;
}
.bred {
  margin-left: 20px;
}
</style>
