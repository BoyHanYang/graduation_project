import http from "@/http";
import { ResetPasswordParm } from "./HomeModel";
//总数统计
export const getTotalApi = ()=>{
    return http.get("/api/home/getTotal")
}
//反馈列表
export const getSuggestListApi = ()=>{
    return http.get("/api/home/getSuggestList")
}
//热销商品
export const getHotGoodstApi = ()=>{
    return http.get("/api/home/getHotGoods")
}
//热销卡
export const getHotCardstApi = ()=>{
    return http.get("/api/home/getHotCards")
}
//热销课程
export const getHotCourseApi = ()=>{
    return http.get("/api/home/getHotCourse")
}
//重置密码
export const resetPasswordApi = (parm:ResetPasswordParm)=>{
    return http.post("/api/home/resetPassword",parm)
}
//修改密码
export const updatePasswordApi = (parm:ResetPasswordParm)=>{
    return http.post("/api/home/updatePassword",parm)
}
//退出登录
export const loginOutApi = ()=>{
    return http.post("/api/home/loginOut")
}