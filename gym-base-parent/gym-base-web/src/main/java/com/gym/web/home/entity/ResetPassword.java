package com.gym.web.home.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/24 1:06
 */

@Data
public class ResetPassword {
    private Integer userId;
    private String userType;
    private String password;
    private String oldPassword;
}