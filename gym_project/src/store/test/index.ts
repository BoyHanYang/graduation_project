import { defineStore } from 'pinia'
//定义store
export const testStore = defineStore('testStore', {
    //定义state
    state: () => {
        return {
            count: 0
        }
    },
    //定义getters
    getters:{
        //获取state里面的count
        getCount(state){
            return state.count;
        }
    },
    //定义actions
    actions:{
        //设置count的值，这里不能使用箭头函数
        setCount(count:number){
            this.count = count;
        }
    }
})