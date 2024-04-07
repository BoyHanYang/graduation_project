import { MemberType } from "@/api/member/MemberModel"
import { EditType } from "@/type/BaseEnum"
import { ref } from "vue"
import { deleteApi } from "@/api/member"
import { ElMessage } from "element-plus"
import { FuncList } from "@/type/BaseType"
import useInstance from "@/hooks/useInstance"
export default function useMember(getList: FuncList) {
    const { global } = useInstance()
    const addRef = ref<{ show: (type: string, row?: MemberType) => void }>()
    //新增
    const addBtn = () => {
        addRef.value?.show(EditType.ADD)
    }
    //编辑
    const editBtn = (row: MemberType) => {
        addRef.value?.show(EditType.EDIT, row)
    }
    //删除
    const deleteBtn = async (row: MemberType) => {
        const confirm = await global.$myconfirm('确定删除该数据吗?')
        if (confirm) {
            let res = await deleteApi(row.memberId)
            if (res && res.code == 200) {
                ElMessage.success(res.msg)
                getList()
            }
        }
    }
    return {
        addBtn,
        editBtn,
        deleteBtn,
        addRef
    }
}