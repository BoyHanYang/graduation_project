import { AddUserModel } from "@/api/user/UserModel"
import { EditType } from "@/type/BaseEnum"
import { ref } from "vue"
import { deleteApi } from "@/api/user"
import { ElMessage } from "element-plus"
import { FuncList } from "@/type/BaseType"
import useInstance from "@/hooks/useInstance"
import { resetPasswordApi } from "@/api/home"

export default function useUser(getList: FuncList) {
    const { global } = useInstance()
    //新增组件的ref属性
    const addRef = ref<{ show: (type: string, row?: AddUserModel) => void }>()
    //新增
    const addBtn = () => {
        //父组件调用子组件的show方法
        addRef.value?.show(EditType.ADD)
    }
    //编辑
    const editBtn = (row: AddUserModel) => {
        //父组件调用子组件的show方法
        addRef.value?.show(EditType.EDIT, row)
    }
    //删除
    const deleteBtn = async (row: AddUserModel) => {
        const confrim = await global.$myconfirm('确定删除该数据吗?')
        if (confrim) {
            let res = await deleteApi(row.userId)
            if (res && res.code == 200) {
                ElMessage.success(res.msg)
                //刷新表格
                getList()
            }
        }
    }
    //重置密码
    const resetPasBtn = async (row: AddUserModel) => {
        const confirm = await global.$myconfirm('确定重置密码吗，重置后密码为【666666】?')
        if (confirm) {
            let parm = {
                userId: row.userId,
                userType: "2"

            }
            let res = await resetPasswordApi(parm)
            if (res && res.code == 200) {
                ElMessage.success(res.msg)
            }
        }

    }
    return {
        addBtn,
        editBtn,
        deleteBtn,
        addRef,
        resetPasBtn
    }
}