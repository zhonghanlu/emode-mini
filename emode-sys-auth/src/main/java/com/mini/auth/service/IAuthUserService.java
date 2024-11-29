package com.mini.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.dto.AuthUserDetailDTO;
import com.mini.auth.model.dto.AuthUserRoleDTO;
import com.mini.auth.model.edit.AuthUserPasswordEdit;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.common.enums.str.UserType;

import java.util.Set;

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

    /**
     * 根据用户名和用户类型查询用户
     */
    AuthUserDTO getUserByUsernameAndUserType(String username, UserType userType);

    /**
     * 根据id查询用户所有权限
     */
    Set<String> getUserPermissionByIdForSet(long id);

    /**
     * 根据id查询用户所有角色
     */
    Set<String> getUserRoleByIdForSet(long id);

    /**
     * 更新密码
     */
    void updatePassword(AuthUserPasswordEdit edit);
}
