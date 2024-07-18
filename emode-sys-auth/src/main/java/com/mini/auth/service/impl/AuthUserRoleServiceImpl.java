package com.mini.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.auth.entity.AuthUserRole;
import com.mini.auth.mapper.AuthUserRoleMapper;
import com.mini.auth.service.IAuthUserRoleService;
import com.mini.common.enums.number.Delete;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zhl
 * @create 2024/6/3 15:45
 */
@Service
@RequiredArgsConstructor
public class AuthUserRoleServiceImpl implements IAuthUserRoleService {

    private final AuthUserRoleMapper authUserRoleMapper;

    @Override
    public long getCountByRoleId(long roleId) {
        LambdaQueryWrapper<AuthUserRole> wrapper = Wrappers.lambdaQuery(AuthUserRole.class);
        wrapper.eq(AuthUserRole::getRoleId, roleId)
                .eq(AuthUserRole::getDelFlag, Delete.NO);
        return authUserRoleMapper.selectCount(wrapper);
    }
}
