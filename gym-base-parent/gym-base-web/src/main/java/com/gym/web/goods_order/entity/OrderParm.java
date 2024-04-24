package com.gym.web.goods_order.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author yangbohan
 * @Date 2024/4/21 17:25
 */

@Data
public class OrderParm {
    private Long userId;
    private List<OrderItem> orderList;
}