package com.mini.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author zhl
 * @create 2024/7/17 10:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaModel {

    @Schema(title = "验证码唯一uuid")
    private String uuid;

    @Schema(title = "验证码")
    private String base64;

    @Schema(title = "验证码是否开启")
    private boolean captchaOnOff;
}
