package com.mini.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mini.auth.entity.AuthUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * ;(auth_user)表数据库访问层
 *
 * @author zhl
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {
}