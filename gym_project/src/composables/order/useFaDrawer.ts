import { AddCar, GoodsType } from "@/api/goods/GoodsModels.ts";
import { ref } from "vue";

export default function useFaDrawer(selectGoods:AddCar) {
    const faDrawer = ref(false)
    const cancelClick = () => {
        faDrawer.value = false;
    };
    const confirmClick = () => {
        faDrawer.value = false;
    };
    const order = ()=>{
        //首次进来，清空购物车数据
        selectGoods.list = []
        faDrawer.value = true;
    }
    //删除购物车数据
    const deleteCarBtn = (row:GoodsType)=>{
        selectGoods.list = selectGoods.list.filter(item => item.goodsId != row.goodsId)
    }
    return {
        faDrawer,
        cancelClick,
        confirmClick,
        order,
        deleteCarBtn
    }
}