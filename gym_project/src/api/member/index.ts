import http from "@/http"
import { ApplyCard, MemberParm, MemberRecharge, MemberType,Recharge } from "./MemberModel"
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
//根据会员id查询角色id
export const getRoleByMemberIdApi = (memberId:string)=>{
    return http.get("/api/member/getRoleByMemberId",{memberId:memberId})
}
//查询会员卡列表
export const getCardListApi = ()=>{
    return http.get("/api/member/getCardList")
}
//办卡提交
export const applySaveApi = (parm:ApplyCard)=>{
    return http.post("/api/member/joinApply",parm)
}
//充值提交
export const rechargeApi = (parm:Recharge)=>{
    return http.post("/api/member/recharge",parm)
}
//我的充值
export const getMyRechargeApi = (parm:MemberRecharge)=>{
    return http.get("/api/member/getMyRecharge",parm)
}