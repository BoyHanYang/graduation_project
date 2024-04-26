import { onMounted, ref } from "vue";
import { getImageApi } from "@/api/login";
export default function useImage(){
    //存放验证码
    const imgSrc = ref()
    //获取验证码
    const getImage = async()=>{
        let res = await getImageApi()
        if(res && res.code == 200){
            imgSrc.value = res.data;
        }
    }
    onMounted(()=>{
        getImage()
    })
    return{
        imgSrc,
        getImage
    }
}