import { reactive } from "vue";
import { getSelectApi } from "../../api/user";
import { SelectRole } from "@/api/user/UserModel";
export default function useSelectRole() {
    //定义角色数据
    const roleData = reactive<SelectRole>({
        list: []
    })
    //获取列表
    const listRole = async () => {
        let res = await getSelectApi()
        if (res && res.code == 200) {
            console.log(res)
            roleData.list = res.data;
        }
    }
    return {
        roleData,
        listRole
    }
}