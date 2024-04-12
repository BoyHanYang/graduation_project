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
        v-for="item in tabList"
        :key="item.path"
        :label="item.title"
        :name="item.path"
    >
      {{ item.content }}
    </el-tab-pane>
  </el-tabs>
</template>

<script setup lang="ts">
import {computed, onMounted, ref, watch} from 'vue'
import {useRoute, useRouter} from "vue-router";
import {tabStore, Tab} from "@/store/tabs/index.ts";
import {TabPaneName} from "element-plus";

const route = useRoute()
const router = useRouter()
const store = tabStore()

// 当前激活的选项卡:当前的路由
const activeTab = ref('')
const tabList = computed(() => {
  return store.getTabs;
})
const editableTabs = ref([
  {
    title: 'Tab 1',
    name: '1',
    content: 'Tab 1 content',
  },
  {
    title: 'Tab 2',
    name: '2',
    content: 'Tab 2 content',
  },
])

const removeTab = (targetName: TabPaneName) => {
  // 首页不能关闭
  if (targetName === '/dashboard') return;
  const tabs = tabList.value
  let activeName = activeTab.value
  if (activeName === targetName) {
    tabs.forEach((tab: Tab, index: number) => {
      if (tab.path === targetName) {
        const nextTab = tabs[index + 1] || tabs[index - 1]
        if (nextTab) {
          activeName = nextTab.path
        }
      }
    })
  }

  activeTab.value = activeName
  store.tabList = tabs.filter((tab: Tab) => tab.path !== targetName)
  router.push({path: activeName});
}
const addTab = () => {
  // 获取选项卡数据：当前的路由
  const {path, meta} = route;
  // 设置选项卡数据
  const tab: Tab = {
    path: path,
    title: meta.title as string,
  };
  store.addTab(tab)
  // 添加到选项卡数据
}
const setActiveTab = () => {
  activeTab.value = route.path
}
watch(
    () => route.path,
    () => {
      //设置激活的选项卡
      setActiveTab();
      //把当前路由添加到选项卡数据
      addTab();
    }
);
const clickBtn = (tab: any) => {
  console.log(tab);
  const {props} = tab;
  console.log(props);
  router.push({path: props.name});
};
/*
// 解决数据刷新丢失的问题
const beforeRefresh = () => {
  window.addEventListener("beforeunload", () => {
    sessionStorage.setItem('tabsView', JSON.stringify(tabList.value));
  });
  let tabSession = sessionStorage.getItem('tabsView')
  if (tabSession) {
    let oldTabs = JSON.parse(tabSession)
    if (oldTabs.length > 0) {
      store.tabList = oldTabs
    }
  }
};
*/

onMounted(() => {
  // beforeRefresh()
  setActiveTab()
  addTab();
})
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