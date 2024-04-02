import { ref } from "vue"

export default function useUser(){
    //新增弹框属性
    const addRef = ref<{show:()=>void}>()
    //新增
    const addBtn = ()=>{
        addRef.value?.show()
    }
    //编辑
    const editBtn = ()=>{

    }
    //删除
    const deleteBtn = ()=>{

    }
    return{
        addBtn,
        editBtn,
        deleteBtn,
        addRef
    }
}