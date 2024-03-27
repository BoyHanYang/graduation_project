package com.gym.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/3/20 20:44
 */

@Data
@AllArgsConstructor
public class ResultVo<T> {
    private String msg;
    private int code;
    private T data;
}