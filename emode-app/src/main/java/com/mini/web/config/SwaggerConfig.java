package com.mini.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
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
    public OpenAPI openApi() {
        return new OpenAPI()
                // 基本信息配置
                .info(new Info()
                        // 标题
                        .title("e-modeApi")
                        // 描述Api接口文档的基本信息
                        .description("Knife4j说明")
                        // 版本
                        .version("openApiV3")
                );
    }
}