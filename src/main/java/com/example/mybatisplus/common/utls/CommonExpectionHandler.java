package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.common.JsonResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class CommonExpectionHandler {

    //SQL异常处理
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse handleSQLException(SQLException e) {
        log.warn(e.getMessage(),e);
        return JsonResponse.failure(e.getMessage());
    }
    //其他异常处理
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse handleBusinessException(Exception e) {
        log.warn(e.getMessage(),e);
        return JsonResponse.failure(e.getMessage());
    }
}

