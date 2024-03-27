package com.gym.web.sys_role.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/3/20 21:46
 */
@Data
public class RoleParm {
    private Long currentPage;
    private Long pageSize;
    private String roleName;
}
