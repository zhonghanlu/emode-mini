package com.mini.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhl
 * @description token 续期配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "token-renew")
public class TokenReNew {

    private int pc;

    private int mini;

}
