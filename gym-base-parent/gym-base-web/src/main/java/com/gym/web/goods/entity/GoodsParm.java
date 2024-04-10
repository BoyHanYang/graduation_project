package com.gym.web.goods.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/10 17:42
 */

@Data
public class GoodsParm {
    private Long currentPage;
    private Long pageSize;
    private String name;
}