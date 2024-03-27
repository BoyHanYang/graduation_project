<template>
  <el-container class="layout">
    <el-aside width="auto" class="asside">
      <menu-bar></menu-bar>
    </el-aside>
    <el-container>
      <el-header class="header" height="50px">
        <Header></Header>
      </el-header>
      <el-main class="main">
        <Tabs style="padding: 0px 20px"></Tabs>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup lang="ts">
import Header from '@/layout/header/Header.vue'
import MenuBar from '@/layout/menu/MenuBar.vue'
import Tabs from "@/layout/tabs/Tabs.vue";

</script>

<style lang="scss">
.layout {
  height: 100%;

  .asside {
    // background-color: blueviolet;
    background-color: #304156;
  }

  .header {
    display: flex;
    align-items: center;
    background-color: #009688;
  }

  .main {
    padding-top: 1px;
    padding-left: 0px !important;
    padding-right: 0px !important;
  }
}
</style>