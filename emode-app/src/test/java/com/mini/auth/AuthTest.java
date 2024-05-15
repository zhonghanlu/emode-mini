package com.mini.auth;
import com.mini.common.enums.number.Delete;
import java.time.LocalDateTime;

import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.biz.auth.AuthBiz;
import com.mini.common.utils.webmvc.IDGenerator;
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
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setId(IDGenerator.next());
        authUserDTO.setUsername("aaa");
        authUserDTO.setPassword("aaa");
        authUserDTO.setNickname("aaa");
        authUserDTO.setAvatar(1L);
        authUserDTO.setPhone("15312665707");
        authUserDTO.setDelFlag(Delete.YES);
        authUserDTO.setDelBy(1L);
        authUserDTO.setCreateBy(1L);
        authUserDTO.setCreateTime(LocalDateTime.now());
        authUserDTO.setUpdateBy(1L);
        authUserDTO.setUpdateTime(LocalDateTime.now());

        authBiz.test(authUserDTO);
        log.info("插入成功");
    }

}
