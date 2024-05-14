package com.mini.web.controller.auth;

import com.mini.auth.entity.AuthUser;
import com.mini.biz.biz.AuthBiz;
import com.mini.biz.mapperstruct.AuthUserStructMapper;
import com.mini.biz.request.auth.AuthUserRequest;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhl
 * @create 2024/5/14 16:37
 */
@Tag(name = "鉴权中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthBiz authBiz;

    @Operation(description = "测试插入")
    @PostMapping("/test")
    public Restful<?> test(AuthUserRequest request) {
        AuthUser authUser = AuthUserStructMapper.INSTANCE.request2Entity(request);
        authBiz.test(authUser);
        return Restful.SUCCESS().build();
    }


}


