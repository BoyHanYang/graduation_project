import {ref} from "vue";
import {CardType} from "@/api/member_card/MemberModel.ts";

export default function useMenu(){
    const addRef = ref<{show:()=>void}>()
    // 新增
    const addBtn = () => {
        addRef.value.show()
    }
    // 编辑
    const delBtn = () => {

    }
    // 删除
    const editBtn = () => {

    }

    return {
        addBtn,
        delBtn,
        editBtn,
        addRef
    }
}