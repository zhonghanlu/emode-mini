package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.entity.AuthRole;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.query.AuthRoleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统角色表;(auth_role)表数据库访问层
 *
 * @author : zhl
 */
@Mapper
public interface AuthRoleMapper extends BaseMapper<AuthRole> {

    IPage<AuthRoleRelationDTO> selectPage(@Param("query") AuthRoleQuery query, IPage<AuthRoleRelationDTO> page);

}