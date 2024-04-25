import { nextTick, onMounted, reactive, ref } from "vue";
import { OrderListParm } from '@/api/order/OrderModel.ts'
import { listApi } from "@/api/goods_order";
export default function useOrderTable() {
    //表格高度
    const orderTableHeight = ref(0)
    //表格数据
    const tableData = reactive({
        list:[]
    })
    //列表查询的参数
    const listParm = reactive<OrderListParm>({
        name: '',
        currentPage: 1,
        pageSize: 20,
        total: 0,
        controlName: ''
    })
    //搜索按钮
    const searchBtn = () => {
        getList()
    }
    //重置按钮
    const resetBtn = () => {
        listParm.name = ''
        getList()
    }
    //页容量改变是触发
    const orderSizeChange = (size:number)=>{
        listParm.pageSize = size;
        getList()
    }
    //页数改变时触发
    const orderCurrentChange  =(page:number)=>{
        listParm.currentPage = page;
        getList()
    }
    //获取列表
    const getList = async()=>{
        let res = await listApi(listParm)
        if(res && res.code == 200){
            tableData.list = res.data.records;
            listParm.total = res.data.total;
        }
    }
    onMounted(()=>{
        getList()
        nextTick(()=>{
            orderTableHeight.value = window.innerHeight - 230
        })
    })
    return {
        listParm,
        searchBtn,
        resetBtn,
        tableData,
        getList,
        orderSizeChange,
        orderCurrentChange,
        orderTableHeight
    }
}