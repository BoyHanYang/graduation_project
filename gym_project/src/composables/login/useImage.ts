import { onMounted, ref } from "vue";
import { getImageApi } from "@/api/login";
export default function useImage(){
    //定义图片src
    const imgSrc = ref()
    //获取图片验证码
    const getImage = async()=>{
        let res = await getImageApi()
        console.log(res)
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