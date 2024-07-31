package com.mini.core.config;

import com.mini.core.aop.ApiMessageAdvisor;
import com.mini.core.aop.OptLogAspect;
import com.mini.core.aop.RateLimiterAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhl
 * aop全局配置
 */
@Configuration
@ComponentScan("com.mini.core.aop")
public class AopConfig {

    @Bean
    public ApiMessageAdvisor createApiMessageAdvisorAspect() {
        return new ApiMessageAdvisor();
    }

    @Bean
    public RateLimiterAspect createRateLimiterAspect() {
        return new RateLimiterAspect();
    }

    @Bean
    public OptLogAspect optLogAspect() {
        return new OptLogAspect();
    }
}
