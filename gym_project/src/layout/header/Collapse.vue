<template>
  <el-icon class="icons" @click="setCollapse">
    <!-- 动态组件的方式显示图标 -->
    <component :is="status ? Fold : Expand "></component>
  </el-icon>
</template>

<script setup lang="ts">
import { Fold, Expand } from "@element-plus/icons-vue";
import { collapseStore } from "@/store/collapse/index";
import { computed } from "vue";
//获取store
const store = collapseStore()
//获取collapse
const status = computed(()=>{
    return !store.getCollapse
})
//改变图标
const setCollapse = ()=>{
    store.setCollapse(!store.getCollapse)
}
</script>

<style scoped lang="scss">
.icons{
    color: #FFF;
    font-size: 26px;
    cursor: pointer;
}
</style>
