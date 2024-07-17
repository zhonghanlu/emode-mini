package com.mini.web.controller.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhl
 * @create 2024/7/17 10:10
 */
@Tag(name = "登录中心", description = "登录中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-auth")
public class LoginController {
}
