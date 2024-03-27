import http from "@/http";
import { AddRoleModel, ListParm } from "./RoleModel";
//新增
export const addApi = (parm: AddRoleModel) => {
    return http.post("/api/role", parm)
}
//编辑
export const editApi = (parm: AddRoleModel) => {
    return http.put("/api/role", parm)
}
//删除
export const deleteApi = (roleId:string)=>{
    return http.delete(`/api/role/${roleId}`)
}
//查询列表
export const getListApi = (parm: ListParm) => {
    return http.get("/api/role/list", parm)
}