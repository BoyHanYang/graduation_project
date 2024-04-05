package com.gym.web.member_card.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/3 15:35
 */
@Data
public class ListCard {
    private Long currentPage;
    private Long pageSize;
    private String title;
}
