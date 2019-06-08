package com.mantou.boot.handler;



import com.mantou.boot.exception.BusinessException;
import com.mantou.boot.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @description 全局异常处理
 * @author mantou
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 请求方法不正确
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        //打印日志
        log("[HttpRequest异常,请求方法不正确]", exception);
        return Result.failed(exception.getMessage());
    }


    /**
     * SysException异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result sysExceptionHandler(BusinessException e){
        //打印日志
        log("[SysException业务异常]", e);
        return Result.failed(e.getMessage());
    }

    /**
     * Exception异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(Exception e){
        //打印日志
        log("[Exception系统异常]", e);
        return Result.failed(e.getMessage());
    }

    /**
     * 打印日志
     * @param e
     */
    private void log(String message, Exception e) {
        log.error("--------------------------------------异常开始------------------------------------------");
        log.error(e.getMessage(), e);
        log.error("--------------------------------------异常结束------------------------------------------");
    }
}
