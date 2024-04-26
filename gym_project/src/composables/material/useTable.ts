import { ListParm } from "@/api/material/MaterialModel";
import { nextTick, onMounted, reactive, ref } from "vue";
import { getListApi } from "@/api/material";
export default function useTable() {
    //表格高度
    const tableHeight = ref(0)
    //定义表格数据
    const tableData = reactive({
        list:[]
    })
    //列表查询参数
    const listParm = reactive<ListParm>({
        name: '',
        currentPage: 1,
        pageSize: 10,
        total: 10
    })
    //获取表格数据
    const getList = async () => {
        let res = await getListApi(listParm)
        if(res && res.code == 200){
            console.log(res)
            tableData.list = res.data.records;
            listParm.total = res.data.total;
        }
    }
    //搜索
    const searchBtn = () => {
        getList()
    }
    //重置
    const resetBtn = () => {
        listParm.name = ''
        listParm.currentPage = 1
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
    const reFresh = ()=>{
        getList()
    }
    onMounted(()=>{
        nextTick(()=>{
            tableHeight.value = window.innerHeight - 230
        })
        getList()
    })
    return {
        listParm,
        searchBtn,
        resetBtn,
        tableData,
        sizeChange,
        currentChange,
        tableHeight,
        reFresh,
        getList
    }
}