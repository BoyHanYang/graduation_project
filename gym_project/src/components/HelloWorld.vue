<template>
  <el-button type="primary" size="default" @click="addBtn">新增</el-button>
  <el-icon><CirclePlus /></el-icon>
  <el-icon><ChatDotRound /></el-icon>
  <el-icon><ChatRound /></el-icon>
  <h3>pinia测试</h3>
  <span>{{count}}</span>
</template>
<script setup lang="ts">
import { storeToRefs } from "pinia"
import { testStore } from "@/store/test/index"
import { computed } from "vue";
// 获取store
const store = testStore();
// 获取count，这种方式会使数据失去响应性，解构后不会失去响应性
// const { count } = storeToRefs(store);
const  count  = computed(() =>{
  return store.count;
}
);
// 新增
const addBtn = () => {
  // 方式一
  // store.count++;
  // 方式二
  // store.setCount(++store.count)
  store.$patch((state) => {
    state.count++;
  });
};
</script>
<style scoped>

</style>
