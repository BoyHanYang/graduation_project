package com.gym.web.goods_order.service;

import com.gym.web.goods_order.entity.GoodsOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.web.home.entity.EchartsItem;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【goods_order】的数据库操作Service
* @createDate 2024-04-21 17:24:36
*/
public interface GoodsOrderService extends IService<GoodsOrder> {
    //热销商品
    List<EchartsItem> hotGoods();
    //热销卡
    List<EchartsItem> hotCards();
    //热销课程
    List<EchartsItem> hotCourse();
}
