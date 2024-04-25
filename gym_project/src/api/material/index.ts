import http from "@/http";
import { MaterialType ,ListParm} from "./MaterialModel";
//新增
export const addApi = (parm:MaterialType)=>{
    return http.post('/api/material',parm)
}
//列表
export const getListApi = (parm:ListParm)=>{
    return http.get("/api/material/list",parm)
}
//编辑
export const editApi = (parm:MaterialType)=>{
    return http.put('/api/material',parm)
}
//删除
export const deleteApi = (id:string)=>{
    return http.delete(`/api/material/${id}`)
}