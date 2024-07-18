package com.mini.core.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.mini.common.enums.str.UserType;
import com.mini.common.utils.LoginUtils;
import com.mini.core.config.properties.SecurityProperties;
import com.mini.core.config.properties.TokenReNew;
import com.mini.core.satoken.PlusSaTokenDao;
import com.mini.core.satoken.SaPermissionImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhl
 * sa-token 配置
 */
@Slf4j
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private TokenReNew tokenReNew;

    /**
     * 注册sa-token的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaInterceptor(handler -> SaRouter
                        // 获取所有的
                        .match("/**")
                        // 对未排除的路径进行检查
                        .check(r -> {
                            // 校验登录
                            StpUtil.checkLogin();
                            // 获取当前人用户类别
                            UserType userType = LoginUtils.getUserType();
                            if (UserType.MINI.equals(userType)) {
                                StpUtil.renewTimeout(tokenReNew.getMini()); // 更新过期时间
                            } else {
                                StpUtil.renewTimeout(tokenReNew.getPc()); // 更新过期时间
                            }
                        }))).addPathPatterns("/**")
                // 排除不需要拦截的路径
                .excludePathPatterns(securityProperties.getExcludes());
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        // Sa-Token 整合 jwt (简单模式)
        return new StpLogicJwtForSimple();
    }

    /**
     * 权限接口实现
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaPermissionImpl();
    }

    /**
     * 自定义dao层存储
     */
    @Bean
    public SaTokenDao saTokenDao() {
        return new PlusSaTokenDao();
    }

}
