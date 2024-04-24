package com.gym.web.goods_order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.goods.entity.Goods;
import com.gym.web.goods.entity.GoodsParm;
import com.gym.web.goods.service.GoodsService;
import com.gym.web.goods_order.entity.GoodsOrder;
import com.gym.web.goods_order.entity.OrderItem;
import com.gym.web.goods_order.entity.OrderParm;
import com.gym.web.goods_order.service.GoodsOrderService;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangbohan
 * @Date 2024/4/21 17:26
 */

@RestController
@RequestMapping("/api/order")
public class GoodsOrderController {
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private GoodsService goodsService;
    //下单
    @PostMapping("/down")
    @PreAuthorize("hasAuthority('sys:orderList:down')")
    public ResultVo down(@RequestBody OrderParm parm){
        //查询用户信息
        SysUser user = sysUserService.getById(parm.getUserId());
        List<OrderItem> list = parm.getOrderList();
        List<GoodsOrder> orderList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Long goodsId = list.get(i).getGoodsId();
            Integer num = list.get(i).getNum();
            //查询商品信息
            Goods goods = goodsService.getById(goodsId);
            GoodsOrder order = new GoodsOrder();
            BeanUtils.copyProperties(goods,order);
            order.setNum(list.get(i).getNum());
            BigDecimal number = BigDecimal.valueOf(list.get(i).getNum());
            BigDecimal price = goods.getPrice();
            BigDecimal total = number.multiply(price);
            BigDecimal totalPrice = total.setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setTotalPrice(totalPrice);
            order.setControlUser(user.getNickName());
            orderList.add(order);
        }
        if(orderList.size() > 0){
            goodsOrderService.saveBatch(orderList);
        }
        return ResultUtils.success("下单成功!");
    }
    //列表
    @GetMapping("/list")
    public ResultVo list(GoodsParm parm){
        //构造分页对象
        IPage<GoodsOrder> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<GoodsOrder> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getName())){
            query.lambda().like(GoodsOrder::getName,parm.getName());
        }
        query.lambda().orderByDesc(GoodsOrder::getCreateTime);
        IPage<GoodsOrder> list = goodsOrderService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }
}