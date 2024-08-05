package com.mini.core.aop;

import com.mini.base.service.ISysUserOptService;
import com.mini.common.enums.str.MethodType;
import com.mini.common.enums.str.YesOrNo;

import java.time.LocalDateTime;

import com.mini.base.entity.SysUserOpt;
import com.mini.base.model.dto.SysUserOptDTO;
import com.mini.common.annotation.OptLog;
import com.mini.common.utils.AddressByIpUtil;
import com.mini.common.utils.JsonUtils;
import com.mini.common.utils.LoginUtils;
import com.mini.common.utils.RequestIdUtils;
import com.mini.common.utils.bean.SpringBean;
import com.mini.common.utils.http.ServletUtil;
import com.mini.common.utils.webmvc.IDGenerator;
import com.mini.common.utils.webmvc.Restful;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 操作日志记录处理
 *
 * @author zhl
 * 参考ruoyi-plus
 */
@Slf4j
@Order(3)
@Aspect
public class OptLogAspect implements Ordered {

    /**
     * 处理完请求后执行
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, OptLog controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, OptLog controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, OptLog controllerLog, final Exception e, Object jsonResult) {
        try {
            SysUserOptDTO sysUserOptDTO = new SysUserOptDTO();
            HttpServletRequest request = ServletUtil.getRequest();
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

            // 获取浏览器信息
            String browserName = userAgent.getBrowser().getName();
            // 获取系统信息
            String systemOs = userAgent.getOperatingSystem().getName();
            // 获取ip
            String clientIP = ServletUtil.getClientIP(request);
            // 获取地址信息
            String region = AddressByIpUtil.getIpPossessionByFile(clientIP);

            // 封装信息
            sysUserOptDTO.setOptStatus(YesOrNo.YES);
            sysUserOptDTO.setUsername(LoginUtils.getUsername());
            sysUserOptDTO.setOptIp(clientIP);
            sysUserOptDTO.setOptAddress(region);
            sysUserOptDTO.setBrowser(browserName);
            sysUserOptDTO.setSystemOs(systemOs);
            sysUserOptDTO.setOptTime(LocalDateTime.now());
            sysUserOptDTO.setOptUrl(request.getRequestURI());
            sysUserOptDTO.setOptMethod(MethodType.get(request.getMethod()));

            // 填充请求参数
            setRequestValue(joinPoint, sysUserOptDTO);

            // 处理全局唯一requestId
            sysUserOptDTO.setRequestId(RequestIdUtils.getRequestId().toString());

            if (Objects.nonNull(e)) {
                sysUserOptDTO.setOptStatus(YesOrNo.NO);
            }

            // 保存数据库
            SpringBean.getBean(ISysUserOptService.class).insertSysUserOpt(sysUserOptDTO);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取请求的参数，放到log中
     */
    private void setRequestValue(JoinPoint joinPoint, SysUserOptDTO optDTO) {
        String requestMethod = optDTO.getOptMethod().getStringValue();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            optDTO.setOptBody(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) Objects.requireNonNull(ServletUtil.getRequest()).getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            optDTO.setOptBody(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (Objects.nonNull(o) && !isFilterObject(o)) {
                    try {
                        params.append(JsonUtils.toJsonString(o)).append(" ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
