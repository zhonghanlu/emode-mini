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
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setId(1612858641088544L);
        authUserDTO.setUsername("aaa");
        authUserDTO.setPassword("aaa");
        authUserDTO.setNickname("aaa");
        authUserDTO.setAvatar(1L);
        authUserDTO.setPhone("15312665707");
        sysUserBiz.add(authUserDTO);
        log.info("插入成功");
    }

}
