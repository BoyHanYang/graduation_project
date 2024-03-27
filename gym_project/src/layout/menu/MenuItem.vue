<template>
  <template v-for="menu in menuList" :key="menu.path">
    <!--    判断当前菜单是否有下级菜单-->
    <el-sub-menu v-if="menu.children && menu.children.length > 0" :index="menu.path">
      <template #title>
        <el-icon>
          <component :is="menu.meta.icon"></component>
        </el-icon>
        <span>{{ menu.meta.title }}</span>
      </template>
      <!--      递归方式展示子菜单-->
      <menu-item :menuList="menu.children"></menu-item>
    </el-sub-menu>
    <el-menu-item style="color: #f4f4f5" v-else :index="menu.path">
      <el-icon>
        <!--          动态组件的方式显示图标-->
        <component :is="menu.meta.icon"></component>
      </el-icon>
      <template #title>{{ menu.meta.title }}</template>
    </el-menu-item>
  </template>
</template>

<script setup lang="ts">
defineProps(["menuList"]);
</script>

<style lang="scss" scoped>

</style>