import { onMounted, reactive, ref } from "vue";
import { listApi } from "@/api/goods";
import { ElMessage } from "element-plus";
import { GoodsParm,GoodsType,AddCar } from "@/api/goods/GoodsModels.ts";
export default function useChildDrawer() {
    const innerDrawer = ref(false)
    const innerCloseClick = () => {
        innerDrawer.value = false;
    };
    //表格高度
    const goodsHeight = ref(0)
    //列表参数
    const goodsParm = reactive<GoodsParm>({
        currentPage: 1,
        pageSize: 20,
        name: '',
        total: 0
    })
    //定义表格数据
    const tableList = reactive({
        list: []
    })
    //获取表格数据
    const getList = async() => {
        let res = await listApi(goodsParm)
        if(res && res.code == 200){
            tableList.list = res.data.records;
            goodsParm.total = res.data.total;
        }
    }
    const innerDrawerShow = ()=>{
        getList()
        innerDrawer.value = true;
    }
    //页容量改变时触发
    const goodsSizeChange = (size:number)=>{
        goodsParm.pageSize = size;
        getList()
    }
    //页数改变时触发
    const goodsCurrentChange = (page:number)=>{
        goodsParm.currentPage = page;
        getList()
    }
    //搜索按钮
    const searchGoodsBtn = ()=>{
        getList()
    }
    //重置
    const resetGoodsBtn = ()=>{
        goodsParm.name = ''
        getList()
    }
    //选择的商品数据
    const selectGoods = reactive<AddCar>({
        list: []
    })
     //加入购物车按钮
     const addCarBtn = (row: GoodsType) => {
        //判断商品是否已经加入购物车
        const flag = selectGoods.list.some(item => item.goodsId === row.goodsId)
        if (flag) {
            ElMessage.warning('该商品已经添加到购物车!');
            return;
        }
        //设置默认数量是1
        row.num = 1;
        selectGoods.list.push(row)
        ElMessage.success('加入成功!');
    }
    onMounted(()=>{
        goodsHeight.value = window.innerHeight - 180
    })
    return {
        innerDrawer,
        innerCloseClick,
        goodsParm,
        getList,
        tableList,
        innerDrawerShow,
        goodsSizeChange,
        goodsCurrentChange,
        searchGoodsBtn,
        resetGoodsBtn,
        goodsHeight,
        addCarBtn,
        selectGoods
    }
}