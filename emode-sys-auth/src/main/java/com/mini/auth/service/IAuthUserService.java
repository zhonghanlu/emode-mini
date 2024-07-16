package com.mini.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.dto.AuthUserDetailDTO;
import com.mini.auth.model.dto.AuthUserRoleDTO;
import com.mini.auth.model.query.AuthUserQuery;

/**
 * @author zhl
 * @create 2024/5/14 15:40
 */
public interface IAuthUserService {

    /**
     * 插入
     */
    void insert(AuthUserDTO authUserDTO);

    /**
     * 删除
     */
    void del(long id);

    /**
     * 更新
     */
    void update(AuthUserDTO authUserDTO);

    /**
     * 查分页
     */
    IPage<AuthUserDTO> selectPage(AuthUserQuery query);

    /**
     * 角色与用户关联关系
     */
    void relationUserAndRole(AuthUserRoleDTO authUserRoleDTO);

    /**
     * 根据用户id查询用户详细角色权限信息
     */
    AuthUserDetailDTO getUserRolePermissionById(long id);

    /**
     * 根据用户id查询用户详细角色信息
     */
    AuthUserDetailDTO getUserRoleById(long id);

    /**
     * 根据用户id查询用户详细权限信息
     */
    AuthUserDetailDTO getUserPermissionById(long id);

    /**
     * 根据用户id查询用户详细信息
     */
    AuthUserDetailDTO getUserById(long id);
}
