package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.model.vo.AuthUserVo;
import com.mini.auth.service.IAuthRoleService;
import com.mini.auth.service.IAuthUserService;
import com.mini.common.utils.SmCryptoUtil;
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
public class SysUserBiz {

    private final IAuthUserService authUserService;

    private final IAuthRoleService authRoleService;

    @Transactional(rollbackFor = Exception.class)
    public void add(AuthUserDTO dto) {
        // 前端传入 sm2 的加密密文
        String password = SmCryptoUtil.doSm2Decrypt(dto.getPassword());
        // 对密码进行解密做 hash
        dto.setPassword(SmCryptoUtil.doHashValue(password));
        authUserService.insert(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(AuthUserDTO dto) {
        authUserService.update(dto);
    }

    public IPage<AuthUserVo> page(AuthUserQuery query) {
        IPage<AuthUserDTO> authUserDTOIPage = authUserService.selectPage(query);
        return authUserDTOIPage.convert(AuthUserStructMapper.INSTANCE::dto2Vo);
    }
}
