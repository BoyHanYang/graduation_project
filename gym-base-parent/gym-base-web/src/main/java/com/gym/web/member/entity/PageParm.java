package com.gym.web.member.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/7 19:59
 */
@Data
public class PageParm {
    private Long currentPage;
    private Long pageSize;
    private String name;
    private String phone;
    private String username;
    private String userType;
    private String memberId;
}
