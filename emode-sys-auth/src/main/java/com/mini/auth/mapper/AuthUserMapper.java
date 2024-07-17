package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.entity.AuthUser;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.dto.AuthUserDetailDTO;
import com.mini.auth.model.query.AuthUserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * ;(auth_user)表数据库访问层
 *
 * @author zhl
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

    /**
     * 分页查询
     */
    IPage<AuthUserDTO> selectPage(@Param("query") AuthUserQuery query, IPage<AuthUserDTO> page);

    /**
     * 根据id获取角色权限信息
     */
    AuthUserDetailDTO getUserRolePermissionById(@Param("id") long id);

    /**
     * 根据id获取角色信息
     */
    AuthUserDetailDTO getUserRoleById(@Param("id") long id);

    /**
     * 根据id获取权限信息
     */
    AuthUserDetailDTO getUserPermissionById(@Param("id") long id);

    /**
     * 根据id获取角色信息
     */
    AuthUserDetailDTO getUserById(@Param("id") long id);

    /**
     * 根据id查询用户所有权限
     */
    Set<String> getUserPermissionByIdForSet(long id);

    /**
     * 根据id查询用户所有角色
     */
    Set<String> getUserRoleByIdForSet(long id);
}