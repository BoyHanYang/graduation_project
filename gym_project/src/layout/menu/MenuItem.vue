<template>
  <template v-for="menu in menuList" :key="menu.path">
    <!-- 判断当前菜单是否有下级，并且下级是否有数据 -->
    <el-sub-menu
      v-if="menu.children && menu.children.length > 0"
      :index="menu.path"
    >
      <template #title>
        <el-icon>
          <!-- 动态组件的方式显示图标 -->
          <component :is="menu.meta.icon"></component>
        </el-icon>
        <span>{{ menu.meta.title }}</span>
      </template>
      <!-- 递归方式展示下级菜单，自己调用自己 -->
      <menu-item :menuList="menu.children"></menu-item>
    </el-sub-menu>
    <el-menu-item  style="color: #f4f4f5" v-else :index="menu.path">
      <el-icon>
        <!-- 动态组件的方式显示图标 -->
        <component :is="menu.meta.icon"></component>
      </el-icon>
      <template #title>{{ menu.meta.title }}</template>
    </el-menu-item>
  </template>
</template>
<script setup lang="ts">
import { ref, reactive } from "vue";
//子组件接收父组件传递的数据
defineProps(["menuList"]);
</script>
<style scoped lang="scss"></style>
