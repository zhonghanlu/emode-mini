package com.mini.web.controller.sys.auth;

import com.mini.auth.model.request.AuthLoginRequest;
import com.mini.auth.model.request.AuthRegisterRequest;
import com.mini.auth.model.vo.AuthUserDetailRouterVo;
import com.mini.auth.model.vo.AuthUserDetailVo;
import com.mini.biz.auth.SysUserBiz;
import com.mini.common.annotation.OptLog;
import com.mini.common.model.LoginModel;
import com.mini.common.utils.webmvc.Restful;
import com.mini.core.config.properties.CaptchaProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    private final CaptchaProperties captchaProperties;

    @Operation(summary = "账户密码登录", description = "密码使用sm-crypto sm2加密传输")
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

    @Operation(summary = "注册", description = "密码使用sm-crypto sm2加密传输")
    @PostMapping("/register")
    public Restful<Void> register(@RequestBody @Valid AuthRegisterRequest request) {
        sysUserBiz.register(request);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "获取用户基本信息")
    @GetMapping("/user-detail-base")
    public Restful<AuthUserDetailVo> getUserInfoBase() {
        return Restful.OBJECT(sysUserBiz.getUserInfoBase()).build();
    }

    @OptLog
    @Operation(summary = "获取用户基本路由信息")
    @GetMapping("/user-detail-router")
    public Restful<List<AuthUserDetailRouterVo>> getUserInfoRouter() {
        return Restful.OBJECT(sysUserBiz.getUserInfoRouter()).build();
    }

    /**
     * 生成验证码
     */
    @OptLog
    @Operation(summary = "获取验证码是否开启")
    @GetMapping("/captcha-enable")
    public Restful<Boolean> getCodeEnable() {
        return Restful.OBJECT(captchaProperties.isEnabled()).build();
    }

}
