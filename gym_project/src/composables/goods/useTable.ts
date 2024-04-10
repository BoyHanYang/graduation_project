import { GoodsParm } from "@/api/goods/GoodsModel";
import { nextTick, onMounted, reactive, ref } from "vue";
import { listApi } from "@/api/goods";
export default function useTable(){
    //表格高度
    const tableHeight = ref(0)
    //表格数据
    const tableData = reactive({
        list:[]
    })
    //列表查询的参数
    const listParm = reactive<GoodsParm>({
        pageSize:10,
        currentPage:1,
        name:'',
        total:0
    })
    //商品列表
    const getList = async()=>{
        let res = await listApi(listParm)
        if(res && res.code == 200){
            console.log(res)
            //设置表格数据
            tableData.list = res.data.records;
            //设置分页总数
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
        listParm.currentPage = 1
        getList()
    }
    //页容量改变触发
    const sizeChange = (size:number)=>{
        listParm.pageSize = size;
        getList()
    }
    //页数改变触发
    const currentChange = (page:number)=>{
        listParm.currentPage =page;
        getList()
    }
    //刷新表格
    const reFresh = ()=>{
        getList()
    }
    onMounted(()=>{
        nextTick(()=>{
            tableHeight.value = window.innerHeight - 230
        })
        getList()
    })
    return{
        listParm,
        searchBtn,
        resetBtn,
        tableData,
        getList,
        sizeChange,
        currentChange,
        tableHeight,
        reFresh
    }
}