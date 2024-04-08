//列表参数类型
export type ListParm = {
    name:string,
    currentPage:number,
    pageSize:number,
    total:number
}
//器材数据类型
export type MaterialType = {
    id:string,
    name:string,
    details:string,
    numTotal:number,
    type:string
}