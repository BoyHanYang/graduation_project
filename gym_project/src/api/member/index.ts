import http from "@/http"
import {MemberParm, MemberType} from "./MemberModel"
//新增
export const addApi = (parm:MemberType)=>{
    return http.post("/api/member",parm)
}
//列表
export const getListApi = (parm:MemberParm)=>{
    return http.get("/api/member/list",parm)
}
//编辑
export const editApi = (parm:MemberType)=>{
    return http.put("/api/member",parm)
}
//删除
export const deleteApi = (memberId:string)=>{
    return http.delete(`/api/member/${memberId}`)
}