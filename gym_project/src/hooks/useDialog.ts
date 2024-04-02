import {DialogModel} from "@/type/BaseType.ts";
import {reactive} from 'vue'
export default function useDialog(){
    //定义弹框属性
    const dialog = reactive<DialogModel>({
        title: '标题',
        visible: false,
        width: 630,
        height: 250
    })

    //展示
    const onShow = () => {
        dialog.visible = true;
    }

    //关闭
    const onClose = () => {
        dialog.visible = false;
    }

    //确定
    const onConfirm = () => {
        dialog.visible = false;
    }

    return {
        dialog,
        onShow,
        onClose,
        onConfirm
    }

}