import http from "@/http"
import {CardType, ListParm} from "@/api/member_card/MemberModel.ts";
export const addApi = (parm:CardType)=>{
    return http.post("/api/memberCard",parm)
}
export const getListApi = (parm:ListParm)=>{
    return http.get("/api/memberCard/list",parm)
}