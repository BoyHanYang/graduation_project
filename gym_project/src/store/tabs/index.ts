import {defineStore} from 'pinia'

export type Tab = {
    title: string,
    path: string
}
export type TabState = {
    tabList: Tab[]
}
// 定义store
export const tabStore = defineStore('tabStore', {
    state: (): TabState => {
        return {
            tabList: []
        }
    },
    getters: {
        getTabs(state) {
            return state.tabList;
        }
    },
    actions:{
        addTab(tab:Tab){
            // 判断是否已经添加数据
            if (this.tabList.some(item =>item.path === tab.path))return;
            this.tabList.push(tab)
        }
    },
    persist: {
        enabled: true,
        strategies: [
            { storage: localStorage, paths: ['tabList'] },
        ],
    }
})