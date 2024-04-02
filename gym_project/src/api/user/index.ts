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