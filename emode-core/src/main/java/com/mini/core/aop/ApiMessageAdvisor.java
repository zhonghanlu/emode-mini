package com.mini.core.aop;

import cn.hutool.json.JSONUtil;
import com.mini.common.constant.HttpStatus;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.JsonUtils;
import com.mini.common.utils.RequestIdUtils;
import com.mini.common.utils.webmvc.Restful;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

/**
 * @author zhl
 * 全局唯一requestId
 */
@Aspect
@Order(1)
@Slf4j
public class ApiMessageAdvisor implements Ordered {


    @Around("execution(public * com.mini.web.controller..*Controller.*(..))")
    public Object invokeAPI(ProceedingJoinPoint pjp) {
        String apiName = this.getApiName(pjp);
        // 生成RequestId
        String requestId = this.getRequestId();
        // 配置日志文件打印 REQUEST_ID
        MDC.put("REQUEST_ID", requestId);
        Object returnValue = null;
        try {
            // 打印请求参数
            this.printRequestParam(apiName, pjp);
            returnValue = pjp.proceed();
            // 处理RequestId
            this.handleRequestId(returnValue);
        } catch (EModeServiceException ex) {
            // 业务异常
            returnValue = this.handleBusinessException(apiName, ex);
        } catch (Throwable ex) {
            // 系统异常        
            returnValue = this.handleSystemException(apiName, ex);
        } finally {
            // 打印响应参数
            this.printResponse(apiName, returnValue);
            RequestIdUtils.removeRequestId();
            MDC.clear();
        }
        return returnValue;
    }

    /**
     * 处理系统异常
     *
     * @param apiName 接口名称
     * @param ex      系统异常
     * @return 返回参数
     */
    private Restful<Object> handleSystemException(String apiName, Throwable ex) {
        log.error("@Meet unknown error when do " + apiName + ":" + ex.getMessage(), ex);
        return Restful.builder().code(HttpStatus.ERROR).msg(ex.getMessage()).requestId(RequestIdUtils.getRequestId().toString()).build();
    }

    /**
     * 处理业务异常
     *
     * @param apiName 接口名称
     * @param ex      业务异常
     * @return 返回参数
     */
    private Restful<Object> handleBusinessException(String apiName, EModeServiceException ex) {
        int code = Objects.nonNull(ex.getCode()) ? ex.getCode() : HttpStatus.ERROR;
        log.error("@Meet error when do " + apiName + "[" + code + "]:" + ex.getMessage(), ex);
        return Restful.builder().code(code).msg(ex.getMessage()).requestId(RequestIdUtils.getRequestId().toString()).build();
    }

    /**
     * 填充RequestId
     *
     * @param returnValue 返回参数
     */
    private void handleRequestId(Object returnValue) {
        if (returnValue instanceof Restful) {
            Restful<?> response = (Restful<?>) returnValue;
            response.setRequestId(RequestIdUtils.getRequestId().toString());
        }
    }

    /**
     * 打印响应参数信息
     *
     * @param apiName     接口名称
     * @param returnValue 返回值
     */
    private void printResponse(String apiName, Object returnValue) {
        if (log.isInfoEnabled()) {
            log.info("@@{} 【done】, response: {}", apiName, JSONUtil.toJsonStr(returnValue));
        }
    }

    /**
     * 打印请求参数信息
     *
     * @param apiName 接口名称
     * @param pjp     切点
     */
    private void printRequestParam(String apiName, ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        if (log.isInfoEnabled() && args != null) {
            for (Object o : args) {
                if (!(o instanceof HttpServletRequest) && !(o instanceof HttpServletResponse) && !(o instanceof MultipartFile)) {
                    log.info("@@{} 【started】, request: {}", apiName, JsonUtils.toJsonString(o));
                }
            }
        }
    }

    /**
     * 获取RequestId
     * 优先从header头获取，如果没有则自己生成
     *
     * @return RequestId
     */
    private String getRequestId() {
        // 因为如果有网关，则一般会从网关传递过来，所以优先从header头获取
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null && StringUtils.hasText(attributes.getRequest().getHeader("x-request-id"))) {
            HttpServletRequest request = attributes.getRequest();
            String requestId = request.getHeader("x-request-id");
            UUID uuid = UUID.fromString(requestId);
            RequestIdUtils.generateRequestId(uuid);
            return requestId;
        }
        UUID existUUID = RequestIdUtils.getRequestId();
        if (existUUID != null) {
            return existUUID.toString();
        }
        RequestIdUtils.generateRequestId();
        return RequestIdUtils.getRequestId().toString();
    }

    /**
     * 获取当前接口对应的类名和方法名
     *
     * @param pjp 切点
     * @return apiName
     */
    private String getApiName(ProceedingJoinPoint pjp) {
        String apiClassName = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        return apiClassName.concat(":").concat(methodName);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}