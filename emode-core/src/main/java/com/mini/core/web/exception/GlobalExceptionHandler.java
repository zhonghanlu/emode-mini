package com.mini.core.web.exception;

import com.mini.common.constant.HttpStatus;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.webmvc.Restful;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Restful<Object> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return Restful.builder().msg("没有权限，请联系管理员授权").code(HttpStatus.FORBIDDEN).build();
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Restful<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                               HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Restful.builder().msg(e.getMessage()).build();
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(EModeServiceException.class)
    public Restful<Object> handleServiceException(EModeServiceException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        return Objects.nonNull(code) ? Restful.builder().msg(e.getMessage()).code(code).build() : Restful.builder().msg(e.getMessage()).code(HttpStatus.ERROR).build();
    }

    /**
     * 请求路径中缺少必需的路径变量
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public Restful<Object> handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求路径中缺少必需的路径变量'{}',发生系统异常.", requestURI, e);
        return Restful.builder().msg(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName())).build();
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Restful<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
        return Restful.builder().msg(String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), Objects.requireNonNull(e.getRequiredType()).getName(), e.getValue())).build();
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Restful<Object> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return Restful.builder().msg(e.getMessage()).build();
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Restful<Object> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return Restful.builder().msg(e.getMessage()).build();
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Restful<Object> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Restful.builder().msg(message).build();
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return Restful.builder().msg(message).build();
    }
}
