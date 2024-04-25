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
        selectGoods.list = []
        faDrawer.value = true;
    }
    //购物车删除
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