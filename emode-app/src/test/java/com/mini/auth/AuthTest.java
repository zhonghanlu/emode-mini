package com.mini.auth;

import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.biz.auth.SysUserBiz;
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
    SysUserBiz sysUserBiz;

    @Test
    void test() {
    }

}
