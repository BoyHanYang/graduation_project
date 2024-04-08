import { MemberType } from "@/api/member/MemberModel";
import { ref } from "vue";

export default function useRecharge(){
    //ref属性
    const rechargeRef = ref<{show:(row:MemberType)=>void}>()
    //充值按钮
    const rechargeBtn = (row:MemberType)=>{
        rechargeRef.value?.show(row)
    }
    return{
        rechargeRef,
        rechargeBtn
    }
}