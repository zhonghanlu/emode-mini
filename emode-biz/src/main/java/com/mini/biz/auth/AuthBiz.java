package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthRoleStructMapper;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.model.vo.AuthRoleRelationVo;
import com.mini.auth.model.vo.AuthUserVo;
import com.mini.auth.service.IAuthRoleService;
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

    private final IAuthRoleService authRoleService;

    @Transactional(rollbackFor = Exception.class)
    public void add(AuthUserDTO dto) {
        authUserService.insert(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(AuthUserDTO dto) {
        authUserService.update(dto);
    }

    public IPage<AuthUserVo> page(AuthUserQuery query) {
        IPage<AuthUserDTO> authUserDTOIPage = authUserService.selectPage(query);
        return authUserDTOIPage.convert(AuthUserStructMapper.INSTANCE::dto2vo);
    }

    /**
     * 角色关联分页
     */
    public IPage<AuthRoleRelationVo> page(AuthRoleQuery query) {
        IPage<AuthRoleRelationDTO> pagedAuthRelation = authRoleService.pageAuthRelation(query);
        return pagedAuthRelation.convert(AuthRoleStructMapper.INSTANCE::dto2Vo);
    }
}
