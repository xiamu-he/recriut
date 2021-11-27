package com.qzx.recruit.handler;

import com.qzx.recruit.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author qzx
 * @create 2021-10-22 21:12
 * @function 捕获全局异常
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 自定义异常
    @ExceptionHandler(UserDefinedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result UserDefined(UserDefinedException exception) {
        return Result.error(exception.getMsg());
    }

    // token 失效异常
    @ExceptionHandler(UnauthorizedExecption.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result Unauthorized(UnauthorizedExecption exception) {
        return Result.unauthorized(exception.getMsg());
    }

    // 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handlerThrowable(Exception exception) {
        log.info("内部错误{}",exception.getMessage());
        return Result.error(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.errorParam(message);
    }
}
