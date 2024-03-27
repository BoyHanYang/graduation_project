package com.gym.web.sys_user.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/3/27 19:19
 */
@Data
public class PageParm {
    private Long currentPage;
    private Long pageSize;
    private String phone;
    private String nickName;
}
