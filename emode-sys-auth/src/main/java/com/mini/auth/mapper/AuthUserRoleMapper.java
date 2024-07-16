package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mini.auth.entity.AuthUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ;(auth_user_role)表数据库访问层
 *
 * @author : zhl
 */
@Mapper
public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {

    int batchInsert(@Param("authUserRoleList") List<AuthUserRole> authUserRoleList);

}