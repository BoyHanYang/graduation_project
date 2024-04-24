package com.gym.web.goods_order.mapper;

import com.gym.web.goods_order.entity.GoodsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.web.home.entity.EchartsItem;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【goods_order】的数据库操作Mapper
* @createDate 2024-04-21 17:24:36
* @Entity com.gym.web.goods_order.entity.GoodsOrder
*/
public interface GoodsOrderMapper extends BaseMapper<GoodsOrder> {
    //热销商品
    List<EchartsItem> hotGoods();
    //热销卡
    List<EchartsItem> hotCards();
    //热销课程
    List<EchartsItem> hotCourse();
}




