package com.gym.web.login.entity;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/14 22:22
 */
@Data
public class UserInfo {
    private Integer userId;
    private String name;
    private Object[] permissons;
}
