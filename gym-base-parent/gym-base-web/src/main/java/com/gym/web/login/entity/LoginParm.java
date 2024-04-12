package com.gym.web.login.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/11 21:03
 */

@Data
public class LoginParm {
    private String username;
    private String password;
    private String code;
    private String userType;
}
