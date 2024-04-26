import { Directive } from 'vue'
import { userStore } from '@/store/user'
export const permission: Directive = {
    mounted(el, binding) {
        //用户的store
        const store = userStore()
        //按钮权限
        const { value } = binding;
        //获取用户的按钮权限字段
        const permissions = store.getCodeList;
        //判断传递进来的按钮权限是否正确
        if(value && value instanceof Array && value.length >0){
            const permissionRoles = value;
            //查看按钮绑定的权限是否在用户的权限字段里面
            const hasPermission = permissions.some((role)=>{
                return permissionRoles.includes(role)
            })
            if(!hasPermission){
                el.style.display = 'none'
            }
        }else{
            throw new Error('need roles! Like v-permission="[\'add\',\'edit\']"')
        }
    }
}