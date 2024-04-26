import { ListParm } from "@/api/user/UserModel";
import { nextTick, onMounted, reactive, ref } from "vue";
import { getListApi } from "@/api/user";
export default function useTable() {
    //表格高度
    const tableHeight = ref(0)
    //表格数据
    const tableList = reactive({
        list:[]
    })
    //查询参数
    const listParm = reactive<ListParm>({
        phone: '',
        pageSize: 10,
        currentPage: 1,
        nickName: '',
        total: 0
    })
    //列表
    const getList = async()=>{
        let res = await getListApi(listParm)
        if(res && res.code == 200){
            //设置表格的数据
            console.log(res.data)
            tableList.list = res.data.records
            //设置分页总条数
            listParm.total = res.data.total
        }
    }
    //搜索
    const searchBtn = ()=>{
        getList()
    }
    //重置
    const resetBtn = ()=>{
        listParm.nickName = ''
        listParm.phone = ''
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
    //刷新列表
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
        tableList,
        listParm,
        getList,
        searchBtn,
        resetBtn,
        sizeChange,
        currentChange,
        tableHeight,
        refresh
    }
}