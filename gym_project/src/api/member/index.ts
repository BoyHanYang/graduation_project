import http from "@/http"
import { MemberType } from "./MemberModel"
//新增
export const addApi = (parm:MemberType)=>{
    return http.post("/api/member",parm)
}
//列表
export const getListApi = (parm:MemberParm)=>{
    return http.get("/api/member/list",parm)
}