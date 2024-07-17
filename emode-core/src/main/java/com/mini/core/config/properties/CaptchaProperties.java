package com.mini.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhl
 * @create 2024/7/17 10:57
 */
@Data
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    private boolean enabled;

    private int weight;

    private int height;

    private int len;

    private int expiration;

}
