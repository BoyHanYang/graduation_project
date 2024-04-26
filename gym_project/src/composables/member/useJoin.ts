import { MemberType } from "@/api/member/MemberModel";
import {getCardListApi} from '@/api/member/index'
import { ref } from "vue";
import { CardType } from "@/api/member_card/MemberModel";

export default function useJoin(){
    //会员卡数据
    const cardList = ref<CardType[]>([])
    //办卡弹框的ref属性
    const joinRef = ref<{show:(row:MemberType)=>void}>()
    //办卡按钮
    const joinBtn = (row:MemberType)=>{
        joinRef.value?.show(row)
    }
    //会员卡列表
    const getCardList = async()=>{
        let res = await getCardListApi()
        if(res && res.code == 200){
            cardList.value = res.data
        }
    }
    return{
        joinRef,
        joinBtn,
        getCardList,
        cardList
    }
}