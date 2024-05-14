package com.mini.core.web.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.mini.common.constant.HttpStatus;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.webmvc.Restful;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author zhl
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 权限码异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public Restful<?> handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestUri, e.getMessage());
        return Restful.FORBIDDEN().build();
    }

    /**
     * 角色权限异常
     */
    @ExceptionHandler(NotRoleException.class)
    public Restful<?> handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',角色权限校验失败'{}'", requestUri, e.getMessage());
        return Restful.FORBIDDEN().build();
    }

    /**
     * 认证失败
     */
    @ExceptionHandler(NotLoginException.class)
    public Restful<?> handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',认证失败'{}',无法访问系统资源", requestUri, e.getMessage());
        return Restful.ERROR_LOGIN().build();
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Restful<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                               HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestUri, e.getMethod());
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
        String requestUri = request.getRequestURI();
        log.error("请求路径中缺少必需的路径变量'{}',发生系统异常.", requestUri, e);
        return Restful.builder().msg(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName())).build();
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Restful<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求参数类型不匹配'{}',发生系统异常.", requestUri, e);
        return Restful.builder().msg(String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), Objects.requireNonNull(e.getRequiredType()).getName(), e.getValue())).build();
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Restful<Object> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestUri, e);
        return Restful.builder().msg(e.getMessage()).build();
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Restful<Object> handleException(Exception e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestUri, e);
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
