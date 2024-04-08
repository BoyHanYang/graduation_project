package com.gym.web.material.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/8 21:03
 */

@Data
public class ListParm {
    private Long currentPage;
    private Long pageSize;
    private String name;
}