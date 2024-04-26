<template>
  <el-button type="primary" size="default" @click="addBtn">新增</el-button>
  <el-icon>
    <Delete />
  </el-icon>
  <el-icon :size="20">
    <Edit />
  </el-icon>
  <el-icon color="#409EFC" class="no-inherit">
    <Share />
  </el-icon>
  <h3>pinia测试</h3>
  <span>{{ count }}</span>
</template>
<script setup lang="ts">
import { computed } from "vue";
import { storeToRefs } from "pinia";
import { testStore } from "@/store/test/index";
//获取store
const store = testStore();
//获取count，这种方式，数据会失去响应性，
//const { count } = store;
//解决响应性丢失的问题
// const { count } = storeToRefs(store);
const count = computed(() => {
  return store.count;
});
//新增
const addBtn = () => {
  //方式一
  // store.count++;
  //方式二
  // store.setCount(++store.count)
  //方式三
  // store.$patch({
  //   count: ++store.count,
  // });
  //方式四
  store.$patch((state)=>{
    state.count = ++state.count
  })
};
</script>

<style scoped></style>
