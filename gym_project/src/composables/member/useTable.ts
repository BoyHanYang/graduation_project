import { MemberParm } from "@/api/member/MemberModel";
import { nextTick, onMounted, reactive, ref } from "vue";
import { getListApi } from "@/api/member";
import { userStore } from "@/store/user";
export default function useTable() {
    const store = userStore()
    //表格高度
    const tableHeight = ref(0)
    //表格数据定义
    const tableList = reactive({
        list:[]
    })
    //参数列表
    const listParm = reactive<MemberParm>({
        name: '',
        phone: '',
        username: '',
        pageSize: 10,
        currentPage: 1,
        total: 0,
        userType:'',
        memberId:''
    })
    //列表
    const getList = async()=>{
        listParm.memberId = store.getUserId
        listParm.userType = store.getUserTyp
        let res = await getListApi(listParm)
        if(res && res.code == 200){
            console.log(res)
            tableList.list = res.data.records
            listParm.total = res.data.total
        }
    }
    //搜索
    const searchBtn = ()=>{
        getList()
    }
    //重置
    const resetBtn = ()=>{
        listParm.name = ''
        listParm.phone = ''
        listParm.username = ''
        getList()
    }
    //页容量改变时触发
    const sizeChange = (size:number)=>{
        listParm.pageSize = size;
        getList()
    }
    //页数改变时触发
    const currentChange = (page:number)=>{
        listParm.currentPage = page;
        getList()
    }
    //刷新表格
    const refresh = ()=>{
        getList()
    }
    onMounted(()=>{
        getList()
        nextTick(()=>{
            tableHeight.value = window.innerHeight - 230
        })
    })
    return{
        listParm,
        getList,
        searchBtn,
        resetBtn,
        tableList,
        sizeChange,
        currentChange,
        tableHeight,
        refresh,
        store
    }
}