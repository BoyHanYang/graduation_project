package com.gym.web.member.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/18 21:48
 */

@Data
public class RechargeParmList {
    private Long currentPage;
    private Long pageSize;
    private Long memberId;
    private String userType;
}
