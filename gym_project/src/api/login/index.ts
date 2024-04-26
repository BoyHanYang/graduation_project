import http from "@/http";
import { LoginParm,InfoParm } from "./LoginModel";
//获取验证码
export const getImageApi = ()=>{
    return http.post("/api/login/image")
}
//登录
export const loginApi = (parm:LoginParm)=>{
    return http.post("/api/login/login",parm)
}
//获取菜单数据
export const getMenuListApi = (parm:InfoParm)=>{
    return http.get("/api/login/getMenuList",parm)
}
//获取用户信息
export const getInfoApi = (parm:InfoParm)=>{
    return http.get("/api/login/getInfo",parm)
}