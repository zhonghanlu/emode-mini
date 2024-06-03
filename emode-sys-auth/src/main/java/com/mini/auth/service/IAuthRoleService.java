package com.mini.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.query.AuthRoleQuery;

/**
 * @author zhl
 * @create 2024/6/3 15:43
 */
public interface IAuthRoleService {

    /**
     * 角色关联分页
     */
    IPage<AuthRoleRelationDTO> pageAuthRelation(AuthRoleQuery authRoleQuery);

}
