package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mini.auth.entity.AuthPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统权限详细记录表;(auth_permission)表数据库访问层
 *
 * @author : zhl
 */
@Mapper
public interface AuthPermissionMapper extends BaseMapper<AuthPermission> {

    /**
     * 批量新增
     */
    int batchInsert(List<AuthPermission> authPermissionList);
}