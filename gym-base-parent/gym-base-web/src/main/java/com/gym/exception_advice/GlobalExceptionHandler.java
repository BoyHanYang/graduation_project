package com.gym.exception_advice;

import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author yangbohan
 * @Date 2024/4/24 18:29
 */

public class GlobalExceptionHandler {
    /**
     * 自定义业务异常拦截
     * BusinessException
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultVo bussinessexception(BusinessException e) {

        return ResultUtils.error(e.getMessage(),e.getCode(),e.getMessage());
    }

    /**
     * 未知的运行时异常拦截
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultVo notFount(RuntimeException e) {
        return ResultUtils.error(e.getMessage(),BusinessExceptionEnum.SERVER_ERROR.getCode(),
                BusinessExceptionEnum.SERVER_ERROR.getMessage());
    }
}
