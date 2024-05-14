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

    @Bean
    public GroupedOpenApi adminApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("BIZ业务")         // 分组名称
                .pathsToMatch("/**")  // 接口请求路径规则
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info() // 基本信息配置
                        .title("fusApi") // 标题
                        .description("Knife4j说明") // 描述Api接口文档的基本信息
                        .version("v1") // 版本
                );

    }
}