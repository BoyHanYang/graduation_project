import { nextTick, onMounted, reactive, ref } from "vue";
import { MyCourseListParm } from '@/api/course/CourseModel'
import { getMyCourseListApi } from "@/api/course";
import { userStore } from "@/store/user";
export default function useMyCourseTable(){
    const store = userStore()
    //表格高度
    const tableHeight = ref(0)
    //定义表格数据
    const tableDate = reactive({
        list:[]
    })
    //列表查询参数
    const listParm = reactive<MyCourseListParm>({
        userId:'',
        userType:'',
        currentPage:1,
        pageSize:10,
        total:0
    })
    //查询列表
    const getList = async()=>{
        listParm.userId = store.getUserId
        listParm.userType = store.getUserTyp
        let res = await getMyCourseListApi(listParm)
        if(res && res.code == 200){
            console.log(res)
            tableDate.list = res.data.records;
            listParm.total = res.data.total;
        }
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
    onMounted(()=>{
        nextTick(()=>{
            tableHeight.value = window.innerHeight - 230
        })
        getList()
    })
    return{
        listParm,
        tableDate,
        getList,
        sizeChange,
        currentChange,
        tableHeight
    }
}