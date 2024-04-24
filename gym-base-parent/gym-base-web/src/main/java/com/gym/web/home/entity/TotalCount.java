package com.gym.web.home.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/24 0:40
 */

@Data
public class TotalCount {
    private Long memberCount;
    private Long userCount;
    private Long materCount;
    private Long orderCount;
}