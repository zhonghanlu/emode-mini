package com.mini.auth;

import com.mini.auth.entity.AuthUser;
import com.mini.biz.biz.AuthBiz;
import com.mini.web.ModeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author zhl
 * @create 2024/5/14 16:46
 */
@Slf4j
@SpringBootTest(classes = ModeApplication.class)
class AuthTest {

    @Resource
    AuthBiz authBiz;

    @Test
    void test() {
        AuthUser authUser = new AuthUser();
        authBiz.test(authUser);
        log.info("插入成功");
    }

}
