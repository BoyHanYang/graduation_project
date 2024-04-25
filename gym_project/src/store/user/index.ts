import { defineStore } from "pinia";
import { getInfoApi } from "@/api/login";
//定义store
export const userStore = defineStore('userStore', {
    state: () => {
        return {
            userId: '',
            token: '',
            userType: '',
            codeList: [],
            name: ''
        }
    },
    getters: {
        getUserId(state) {
            return state.userId
        },
        getToken(state) {
            return state.token
        },
        getUserTyp(state) {
            return state.userType
        },
        getCodeList(state){
            return state.codeList
        }
    },
    actions: {
        setUserId(userId: string) {
            this.userId = userId;
        },
        setToken(token: string) {
            this.token = token;
        },
        setUserType(userType: string) {
            this.userType = userType
        },
        getInfo() {
            return new Promise((reslove, reject) => {
                getInfoApi({ userId: this.userId, userType: this.userType }).then((res) => {
                    if (res && res.code == 200) {
                        this.codeList = res.data.permissons
                        this.name = res.data.name
                    }
                    reslove(this.codeList)
                }).catch((error) => {
                    reject(error)
                })
            })
        }
    },
    persist: {
        enabled: true, //开启持久化操作,默认全部字段存储，存储在sessionSotrage里面
        strategies: [
            { storage: localStorage, paths: ['userId', 'token','codeList','name','userType'] },
        ]
    }
})