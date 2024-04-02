import http from "@/http";
import { AddUserModel, ListParm } from "./UserModel";
//获取角色列表
export const getSelectApi =()=>{
    return http.get("/api/role/getSelect")
}
//新增
export const addApi = (parm:AddUserModel)=>{
    return http.post("/api/user",parm)
}
//用户列表
export const getListApi = (parm:ListParm)=>{
    return http.get("/api/user/list",parm)
}
//编辑
export const editApi = (parm:AddUserModel)=>{
    return http.put('/api/user',parm)
}
//删除
export const deleteApi = (userId:string)=>{
    return http.delete(`/api/user/${userId}`)
}
//根据用户id查询角色
export const getRoleApi = (userId:string)=>{
    return http.get("/api/user/role",{userId:userId})
}