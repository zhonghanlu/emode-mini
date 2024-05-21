package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.entity.AuthUser;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.query.AuthUserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ;(auth_user)表数据库访问层
 *
 * @author zhl
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

    IPage<AuthUserDTO> selectPage(@Param("query") AuthUserQuery query, IPage<AuthUserDTO> page);

}