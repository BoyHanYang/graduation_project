package com.gym.web.goods_order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.goods_order.entity.GoodsOrder;
import com.gym.web.goods_order.service.GoodsOrderService;
import com.gym.web.goods_order.mapper.GoodsOrderMapper;
import com.gym.web.home.entity.EchartsItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【goods_order】的数据库操作Service实现
* @createDate 2024-04-21 17:24:36
*/
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder>
    implements GoodsOrderService{

    @Override
    public List<EchartsItem> hotGoods() {
        return this.baseMapper.hotGoods();
    }

    @Override
    public List<EchartsItem> hotCards() {
        return this.baseMapper.hotCards();
    }

    @Override
    public List<EchartsItem> hotCourse() {
        return this.baseMapper.hotCourse();
    }
}




