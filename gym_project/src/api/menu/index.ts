import http from "@/http";
import {MenuType} from "@/api/menu/MenuModel.ts";
export const getParentApi =()=>{
    return http.get("/api/menu/parent")
}
//新增
export const addApi = (parm:MenuType)=>{
    return http.post("/api/menu",parm)
}
//列表
export const getListApi = ()=>{
    return http.get("/api/menu/list")
}
//编辑
export const editApi = (parm:MenuType)=>{
    return http.put("/api/menu",parm)
}
//删除
export const deleteApi = (menuId:string | number)=>{
    return http.delete(`/api/menu/${menuId}`)
}