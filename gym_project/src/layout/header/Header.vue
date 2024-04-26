<template>
  <div class="header-container">
    <Collapse></Collapse>
    <bread-crumb></bread-crumb>
  </div>
  <LoginOut></LoginOut>
</template>
<script setup lang="ts">
import { ref, reactive } from "vue";
import LoginOut from "./LoginOut.vue";
import Collapse from "./Collapse.vue";
import BreadCrumb from "./BreadCrumb.vue";
</script>
<style scoped lang="scss">
.header-container {
  display: flex;
  align-items: center;
}
</style>
