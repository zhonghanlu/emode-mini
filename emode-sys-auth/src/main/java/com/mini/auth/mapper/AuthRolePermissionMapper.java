package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mini.auth.entity.AuthRolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ;(auth_role_permission)表数据库访问层
 *
 * @author : zhl
 */
@Mapper
public interface AuthRolePermissionMapper extends BaseMapper<AuthRolePermission> {

    /**
     * 批量新增
     */
    int batchInsert(List<AuthRolePermission> authRolePermissionList);

    /**
     * 批量修改
     */
    int batchUpdate(List<AuthRolePermission> authRolePermissionList);
}