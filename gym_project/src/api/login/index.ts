import http from "@/http/index";
import { LoginParm } from "./LoginModel";
//获取验证码
export const getImageApi = ()=>{
    return http.post("/api/login/image")
}
//登录
export const loginApi = (parm:LoginParm)=>{
    return http.post("/api/login/login",parm)
}