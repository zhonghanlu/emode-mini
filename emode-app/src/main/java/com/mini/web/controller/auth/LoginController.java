package com.mini.web.controller.auth;

import com.mini.auth.model.request.AuthLoginRequest;
import com.mini.auth.model.request.AuthRegisterRequest;
import com.mini.biz.auth.SysUserBiz;
import com.mini.common.model.LoginModel;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    private final SysUserBiz sysUserBiz;

    @Operation(summary = "账户密码登录")
    @PostMapping("/login")
    public Restful<LoginModel> login(@RequestBody @Valid AuthLoginRequest request) {
        return Restful.OBJECT(sysUserBiz.login(request)).build();
    }

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public Restful<Void> logout() {
        sysUserBiz.logout();
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "注册")
    @PostMapping("/register")
    public Restful<Void> register(@RequestBody @Valid AuthRegisterRequest request) {
        sysUserBiz.register(request);
        return Restful.SUCCESS().build();
    }

}
