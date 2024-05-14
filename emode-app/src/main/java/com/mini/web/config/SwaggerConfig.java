package com.mini.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhl
 * 业务相关配置
 **/
@Configuration
public class SwaggerConfig {

    /**
     * 创建了一个api接口的分组
     */
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                // 分组名称
                .group("BIZ业务")
                // 接口请求路径规则
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                // 基本信息配置
                .info(new Info()
                        // 标题
                        .title("fusApi")
                        // 描述Api接口文档的基本信息
                        .description("Knife4j说明")
                        // 版本
                        .version("v1")
                );

    }
}