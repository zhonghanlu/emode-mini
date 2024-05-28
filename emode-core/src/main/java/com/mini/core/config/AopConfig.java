package com.mini.core.config;

import com.mini.core.aop.ApiMessageAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mini.core.aop")
public class AopConfig {

    @Bean
    public ApiMessageAdvisor createAop() {
        return new ApiMessageAdvisor();
    }
}
