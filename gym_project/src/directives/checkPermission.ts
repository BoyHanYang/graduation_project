import { userStore } from '@/store/user'
export default function checkPermission(value:any) {
    //用户的store
    const store = userStore()
    //获取用户的按钮权限字段
    const permissions = store.getCodeList;
    //判断传递进来的按钮权限是否正确
    if (value && value instanceof Array && value.length > 0) {
        const permissionRoles = value;
        //查看按钮绑定的权限是否在用户的权限字段里面
        const hasPermission = permissions.some((role) => {
            return permissionRoles.includes(role)
        })
        return hasPermission
    } else {
        throw new Error('need roles! Like v-permission="[\'add\',\'edit\']"')
    }
}