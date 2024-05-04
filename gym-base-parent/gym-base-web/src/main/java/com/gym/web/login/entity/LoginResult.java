package com.gym.web.login.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/12 14:40
 */
@Data
public class LoginResult {
    private Integer userId;
    private String username;
    private String token;
    private String userType;
}