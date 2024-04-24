package com.gym.exception_advice;

/**
 * @Author yangbohan
 * @Date 2024/4/24 18:27
 */

public class BusinessException extends RuntimeException {
    private Integer code;

    private String message;

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}