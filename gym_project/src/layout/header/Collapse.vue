<template>
  <el-icon class="icons" @click="setCollapse">
    <component :is="status ?Fold:Expand"/>
  </el-icon>
</template>

<script setup lang="ts">
import {computed} from "vue";
import {Fold, Expand} from "@element-plus/icons-vue";
import {collapseStore} from "@/store/collapse/index.ts"

const store = collapseStore()
const status = computed(() => {
  return !store.getCollapse;
});
const setCollapse = () => {
  store.setCollapse(!store.getCollapse)
}
</script>

<style lang="scss" scoped>
.icons {
  color: #f4f4f5;
  font-size: 26px;
  cursor: pointer;
}
</style>