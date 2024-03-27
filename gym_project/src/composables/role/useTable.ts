import {nextTick, onMounted, reactive, ref} from "vue";
import {ListParm} from "@/api/role/RoleModel.ts";
import {getListApi} from "@/api/role";

export default function useTable() {
    // 定义表格高度
    const tableHeight = ref(0)
    // 定义表格数据
    const tableList = reactive({
        list: []
    })
    //列表参数
    const listParm = reactive<ListParm>({
        roleName: '',
        currentPage: 1,
        pageSize: 10,
        total: 0
    })
    // 获取列表
    const getList = async () => {
        let res = await getListApi(listParm);
        if (res && res.code == 200) {
            console.log(res)
            // 设置表格数据
            tableList.list = res.data.records
            //设置分页的总条数
            listParm.total = res.data.total
        }
    }
    // 搜索
    const searchBtn = () => {
        getList();
    }
    // 重置
    const resetBtn = () => {
        listParm.roleName = '';
        getList();
    }
    // 刷新列表
    const refresh = () => {
        getList();
    }
    // 页容量改变
    const sizeChange = (val: number) => {
        listParm.pageSize = val;
        getList();
    }
    // 页码改变
    const currentChange = (val: number) => {
        listParm.currentPage = val;
        getList();
    }
    onMounted(()=>{
        getList()
        nextTick(()=>{
            tableHeight.value = window.innerHeight - 230
        })
    })
    return {
        listParm,
        getList,
        searchBtn,
        resetBtn,
        tableList,
        sizeChange,
        currentChange,
        tableHeight,
        refresh
    }

}