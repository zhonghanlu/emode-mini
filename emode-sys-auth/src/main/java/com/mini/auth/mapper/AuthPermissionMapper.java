package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.entity.AuthPermission;
import com.mini.auth.model.dto.AuthPermissionDTO;
import com.mini.auth.model.query.AuthPermissionQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 分页查询
     */
    IPage<AuthPermissionDTO> selectPage(@Param("query") AuthPermissionQuery query, IPage<AuthPermissionDTO> page);
}