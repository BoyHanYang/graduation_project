import http from "@/http";
import { OrderListParm } from '@/api/order/OrderModel'
//下单
export const downApi = (parm:any)=>{
    return http.post("/api/order/down",parm)
}
//列表
export const listApi = (parm:OrderListParm)=>{
    return http.get("/api/order/list",parm)
}