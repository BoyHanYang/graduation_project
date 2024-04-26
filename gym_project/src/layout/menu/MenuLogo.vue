<template>
  <div class="logo">
    <img :src="MenuLogo" />
    <span v-if="show" class="logo-title">{{ title }}</span>
  </div>
</template>
<script setup lang="ts">
import { ref, watch } from "vue";
import MenuLogo from "@/assets/MenuLogo.png";
// const MenuLogo = ref('https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png')
const title = ref("健身管理系统");
//接收父组件传递第值
const props = defineProps({
  isCollapsed: {
    require: true,
    type: Boolean,
  },
});
const show = ref(true);
//解决logo文章僵硬的问题
watch(
  () => props.isCollapsed,
  (collapsed: boolean) => {
    if (!collapsed) {
      setTimeout(() => {
        show.value = !collapsed;
      }, 300);
    } else {
      show.value = !collapsed;
    }
  }
);
</script>
<style scoped lang="scss">
.logo {
  display: flex;
  width: 100%;
  height: 50px;
  line-height: 50px;
  background: #2b2f3a;
  text-align: center;
  cursor: pointer;
  align-items: center;
  img {
    width: 36px;
    height: 36px;
    margin-right: 12px;
    margin-left: 20px;
  }
  .logo-title {
    color: #fff;
    font-weight: 800;
    line-height: 50px;
    font-size: 22px;
    font-family: FangSong;
  }
}
</style>
