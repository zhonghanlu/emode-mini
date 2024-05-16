package com.mini.biz.auth;

import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.service.IAuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhl
 * @create 2024/5/14 16:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthBiz {

    private final IAuthUserService authUserService;

    @Transactional(rollbackFor = Exception.class)
    public void add(AuthUserDTO dto) {
        authUserService.insert(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(AuthUserDTO dto) {
        authUserService.update(dto);
    }
}
