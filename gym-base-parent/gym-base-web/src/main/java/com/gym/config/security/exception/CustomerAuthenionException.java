package com.gym.config.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author yangbohan
 * @Date 2024/4/24 18:21
 */

public class CustomerAuthenionException extends AuthenticationException {
    public CustomerAuthenionException(String msg) {
        super(msg);
    }
}
