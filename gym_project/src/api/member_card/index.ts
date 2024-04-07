import http from "@/http"
import {CardType, ListParm} from "@/api/member_card/MemberModel.ts";
export const addApi = (parm:CardType)=>{
    return http.post("/api/memberCard",parm)
}
export const getListApi = (parm:ListParm)=>{
    return http.get("/api/memberCard/list",parm)
}
// 编辑
export const editApi = (parm:CardType)=>{
    return http.put("/api/memberCard",parm)
}
// 删除
export const deleteApi = (cardId:string)=>{
    return http.delete(`/api/memberCard/${cardId}`)
}