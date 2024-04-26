import http from "@/http";
import { CardType, ListParm } from "./MemberModel";
//新增
export const addApi = (parm:CardType)=>{
    return http.post("/api/memberCard",parm)
}
//列表
export const getListApi = (parm:ListParm)=>{
    return http.get("/api/memberCard/list",parm)
}
//编辑
export const editApi = (parm:CardType)=>{
    return http.put("/api/memberCard",parm)
}
//删除
export const deleteApi = (cardId:string)=>{
    return http.delete(`/api/memberCard/${cardId}`)
}