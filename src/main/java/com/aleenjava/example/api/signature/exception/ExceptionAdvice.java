package com.aleenjava.example.api.signature.exception;

import com.aleenjava.example.api.signature.bean.ApiResult;
import com.aleenjava.example.api.signature.sign.core.ApiSignValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常拦截器
 * @author cheng
 */
@RestControllerAdvice
public class ExceptionAdvice {


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ApiSignValidException.class)
    public ApiResult handle(ApiSignValidException e) {
        return ApiResult.fail("401", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult handle(Exception e) {
        return ApiResult.fail("500", "服务器开小差");
    }

}
