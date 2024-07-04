package com.mini.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.dto.AuthPermissionDTO;
import com.mini.auth.model.query.AuthPermissionQuery;

/**
 * @author zhl
 * @create 2024/6/3 15:43
 */
public interface IAuthPermissionService {

    /**
     * 新增权限信息
     */
    void insert(AuthPermissionDTO dto);

    /**
     * 删除权限信息
     */
    void del(long id);

    /**
     * 修改权限信息
     */
    void update(AuthPermissionDTO dto);

    /**
     * 权限分页
     */
    IPage<AuthPermissionDTO> pagePermission(AuthPermissionQuery authPermissionQuery);

}
