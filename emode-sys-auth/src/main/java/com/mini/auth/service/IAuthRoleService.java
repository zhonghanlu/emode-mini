package com.mini.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.dto.AuthRoleDTO;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.query.AuthRoleQuery;

import java.util.List;

/**
 * @author zhl
 * @create 2024/6/3 15:43
 */
public interface IAuthRoleService {

    /**
     * 角色关联分页
     */
    IPage<AuthRoleRelationDTO> pageAuthRelation(AuthRoleQuery authRoleQuery);

    /**
     * 所有角色
     */
    List<AuthRoleDTO> all();

    /**
     * 根据角色id查询角色详细权限信息
     */
    AuthRoleRelationDTO getRoleById(long id);

    /**
     * 新增角色信息
     */
    void insert(AuthRoleRelationDTO dto);

    /**
     * 删除角色信息
     */
    void del(long id);

    /**
     * 修改角色信息
     */
    void update(AuthRoleRelationDTO dto);

    /**
     * 根据角色名获取信息
     */
    boolean checkRoleByRoleName(long id, String roleName);

    /**
     * 根据角色码值获取信息
     */
    boolean checkRoleByRoleCode(long id, String roleCode);

}
