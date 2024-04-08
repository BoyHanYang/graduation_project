package com.gym.web.member.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author yangbohan
 * @Date 2024/4/7 21:17
 */
@Data
public class RechargeParm {
    private Integer userId;
    private Integer memberId;
    private BigDecimal money;
}
