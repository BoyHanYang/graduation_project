<template>
  <el-tabs
    v-model="activeTab"
    type="card"
    class="demo-tabs"
    closable
    @tab-remove="removeTab"
    @tab-click="clickBtn"
  >
    <el-tab-pane
      v-for="item in tabsList"
      :key="item.path"
      :label="item.title"
      :name="item.path"
    >
    </el-tab-pane>
  </el-tabs>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { TabPanelName, TabsPaneContext } from "element-plus";
import { computed, onMounted, ref, watch } from "vue";
import { tabStore, Tab } from "@/store/tabs/index";
//获取router 和 当前路由
const route = useRoute();
const router = useRouter();
//获取sore
const store = tabStore();

//当前激活的选项卡：当前路由
const activeTab = ref("");
//选项卡数据
const tabsList = computed(() => {
  return store.getTabs;
});

const removeTab = (targetName: TabPanelName) => {
  //首页不能关闭
  if (targetName === "/dashboard") return;
  const tabs = tabsList.value;
  let activeName = activeTab.value;
  if (activeName === targetName) {
    tabs.forEach((tab, index) => {
      if (tab.path === targetName) {
        const nextTab = tabs[index + 1] || tabs[index - 1];
        if (nextTab) {
          activeName = nextTab.path;
        }
      }
    });
  }
  activeTab.value = activeName;
  store.tabList = tabs.filter((tab) => tab.path !== targetName);
  //跳转路由
  router.push({ path: activeName });
};
//添加菜单到选项卡
const addTab = () => {
  //获取选项卡数据：当前的路由
  const { path, meta } = route;
  //设置选项卡数据
  const tab: Tab = {
    path: path,
    title: meta.title as string,
  };
  //添加到选项卡数据
  store.addTab(tab);
};
//当前激活的选项卡
const setActiveTab = () => {
  activeTab.value = route.path;
};
//监听路由,设置选项卡的数据
watch(
  () => route.path,
  () => {
    //激活当前的选项卡
    setActiveTab();
    //把当前菜单设置到选项卡
    addTab();
  }
);
//选项卡点击事件
const clickBtn = (tab: TabsPaneContext) => {
  console.log(tab);
  //props : 当前点击的选项卡数据
  const { props } = tab;
  //路由跳转
  router.push({ path: props.name as string });
};

//解决刷新数据丢失的问题
// const beforeRefresh = () => {
//   window.addEventListener("beforeunload", () => {
//     sessionStorage.setItem("tabsView", JSON.stringify(tabsList.value));
//   });
//   let tabSession = sessionStorage.getItem("tabsView");
//   if (tabSession) {
//     let oldTabs = JSON.parse(tabSession);
//     if (oldTabs.length > 0) {
//       store.tabList = oldTabs;
//     }
//   }
// };
onMounted(() => {
  // beforeRefresh();
  setActiveTab();
  addTab();
});
</script>


<style scoped lang="scss">
:deep(.el-tabs__header) {
  margin: 0px;
}
:deep(.el-tabs__item) {
  height: 26px !important;
  line-height: 26px !important;
  text-align: center !important;
  border: 1px solid #d8dce5 !important;
  margin: 0px 3px !important;
  color: #495060;
  font-size: 12px !important;
  padding: 0xp 10px !important;
}
:deep(.el-tabs__nav) {
  border: none !important;
}
:deep(.is-active) {
  border-bottom: 1px solid transparent !important;
  border: 1px solid #42b983 !important;
  background-color: #42b983 !important;
  color: #fff !important;
}
:deep(.el-tabs__item:hover) {
  color: #495060 !important;
}
:deep(.is-active:hover) {
  color: #fff !important;
}
:deep(.el-tabs__nav-next){
  line-height: 26px !important;
}
:deep(.el-tabs__nav-prev){
  line-height: 26px !important;
}
</style>
