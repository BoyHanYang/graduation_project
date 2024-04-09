<template>
  <el-main>
    <!--    新增-->
    <el-button type="primary" :icon="Plus" @click="addBtn" size="default">新增</el-button>
    <!--    新增编辑-->
    <add-menu ref="addRef"></add-menu>
  </el-main>
</template>

<script setup lang="ts">
import AddMenu from "@/views/system/menu/AddMenu.vue";
import {Plus, Edit, Delete, Search, Close} from "@element-plus/icons-vue";
import useMenu from "@/composables/menu/useMenu.ts";
// 新增编辑
const {addBtn, editBtn, deleteBtn, addRef} = useMenu();
</script>

<style scoped>

</style>