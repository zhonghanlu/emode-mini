package com.mini.biz.biz;

import com.mini.auth.entity.AuthUser;
import com.mini.auth.service.IAuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhl
 * @create 2024/5/14 16:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthBiz {

    private final IAuthUserService authUserService;

    public void test(AuthUser authUser) {
        authUserService.insert(authUser);
    }
}
