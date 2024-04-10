package com.gym.web.suggest.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/10 21:13
 */

@Data
public class SuggestParm {
    private Long currentPage;
    private Long pageSize;
    private String title;
}