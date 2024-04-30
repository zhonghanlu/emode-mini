package com.mini.common.mybatis.handler;

import com.mini.common.constant.HttpStatus;
import com.mini.common.utils.webmvc.Restful;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Mybatis异常处理器
 *
 * @author Lion Li
 */
@Slf4j
@RestControllerAdvice
public class MybatisExceptionHandler {

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Restful<Object> handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',数据库中已存在记录'{}'", requestURI, e.getMessage());
        return Restful.builder().code(HttpStatus.BAD_REQUEST).msg("数据库中已存在该记录，请联系管理员确认").build();
    }

    /**
     * Mybatis系统异常 通用处理
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public Restful<Object> handleCannotFindDataSourceException(MyBatisSystemException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        if ("CannotFindDataSourceException".contains(message)) {
            log.error("请求地址'{}', 未找到数据源", requestURI);
            return Restful.builder().code(HttpStatus.BAD_REQUEST).msg("未找到数据源，请联系管理员确认").build();
        }
        log.error("请求地址'{}', Mybatis系统异常", requestURI, e);
        return Restful.builder().code(HttpStatus.BAD_REQUEST).msg(message).build();
    }

}
