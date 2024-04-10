package com.gym.web.lost.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/10 17:39
 */
@Data
public class LostParm {
    private Long currentPage;
    private Long pageSize;
    private String lostName;
}
