package com.example.myweb.exception;

import com.example.myweb.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //錯誤處理
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error((StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失敗"));
    }
}
