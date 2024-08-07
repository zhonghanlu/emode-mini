package com.mini.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author zhl
 * @create 2024/7/17 15:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {

    @Schema(title = "token")
    private String token;

}
