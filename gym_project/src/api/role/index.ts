import http from "@/http";
import { AddRoleModel, AssignParm, ListParm, SaveAssignParm } from "./RoleModel";
//新增 
export const addApi = (parm:AddRoleModel)=>{
    return http.post('/api/role',parm)
}
//查询
export const getListApi =(parm:ListParm)=>{
    return http.get('/api/role/list',parm)
}
//编辑
export const editApi = (parm:AddRoleModel)=>{
    return http.put('/api/role',parm)
}
//删除
export const deleteApi = (roleId:string)=>{
    return http.delete(`/api/role/${roleId}`)
}
//查询权限树
export const getMenuTreeApi =(parm:AssignParm)=>{
    return http.get('/api/role/getMenuTree',parm)
}
//保存权限
export const saveRoleMenuApi =(parm:SaveAssignParm)=>{
    return http.post('/api/role/saveRoleMenu',parm)
}
