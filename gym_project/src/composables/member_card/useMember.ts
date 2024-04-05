import {ref} from "vue";

export default function useMember() {
    // 弹窗ref属性
    const addRef = ref<{show:()=>void}>()
    // 新增
    const addBtn = () => {
        addRef.value?.show()
    }
    // 编辑
    const editBtn = () => {
        console.log("编辑")
    }
    // 删除
    const deleteBtn = () => {
        console.log("删除")
    }
    return {
        addRef,
        addBtn,
        editBtn,
        deleteBtn
    }
}
