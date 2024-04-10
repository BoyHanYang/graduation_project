//列表查询参数类型
export type GoodsParm = {
    currentPage:number,
    pageSize:number,
    name:string
}
//商品数据类型
export type GoodsType = {
    type:string,
    goodsId:string,
    name:string,
    image:string,
    details:string,
    unit:string,
    specs:string,
    price:number,
    store:number,
}