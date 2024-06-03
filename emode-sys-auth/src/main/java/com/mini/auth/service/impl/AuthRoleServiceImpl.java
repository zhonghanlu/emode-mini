package com.mini.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapper.AuthRoleMapper;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.service.IAuthRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhl
 * @create 2024/6/3 15:45
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRoleServiceImpl implements IAuthRoleService {

    private final AuthRoleMapper authRoleMapper;

    @Override
    public IPage<AuthRoleRelationDTO> pageAuthRelation(AuthRoleQuery authRoleQuery) {
        return authRoleMapper.selectPage(authRoleQuery, authRoleQuery.build());
    }
}
