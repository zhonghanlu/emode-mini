package com.mini.web.controller.auth;

import com.mini.common.annotation.OptLog;
import com.mini.common.annotation.RateLimiter;
import com.mini.common.constant.RedisConstant;
import com.mini.common.enums.LimitType;
import com.mini.common.model.CaptchaModel;
import com.mini.common.utils.redis.RedisUtils;
import com.mini.common.utils.webmvc.Restful;
import com.mini.core.config.properties.CaptchaProperties;
import io.springboot.captcha.SpecCaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

/**
 * @author zhl
 * @create 2024/7/17 10:15
 */
@Tag(name = "验证码中心", description = "验证码中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-captcha")
public class CaptchaController {

    private final CaptchaProperties captchaProperties;

    /**
     * 生成验证码
     */
    @OptLog
    @RateLimiter(time = 1, count = 2, limitType = LimitType.IP)
    @Operation(summary = "生成验证码")
    @GetMapping("/captcha-image")
    public Restful<CaptchaModel> getCode() {
        CaptchaModel captchaModel = new CaptchaModel();
        if (!captchaProperties.isEnabled()) {
            captchaModel.setCaptchaOnOff(Boolean.FALSE);
            return Restful.OBJECT(captchaModel).build();
        }

        SpecCaptcha specCaptcha = new SpecCaptcha(captchaProperties.getWeight(), captchaProperties.getHeight(), captchaProperties.getLen());
        String captchaCode = specCaptcha.text().toLowerCase();
        String uuid = String.valueOf(UUID.randomUUID());
        String redisKey = RedisConstant.CAPTCHA_CODE_KEY + uuid;
        // 存入redis并设置过期时间为5分钟
        RedisUtils.setCacheObject(redisKey, captchaCode, Duration.ofMinutes(captchaProperties.getExpiration()));
        // 将model回传给前端
        captchaModel = CaptchaModel.builder()
                .base64(specCaptcha.toBase64())
                .captchaOnOff(captchaProperties.isEnabled())
                .uuid(uuid)
                .build();
        return Restful.OBJECT(captchaModel).build();
    }
}
