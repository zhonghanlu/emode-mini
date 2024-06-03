package com.mini.auth.mapperstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/6/3 15:39
 */
@Mapper
public interface AuthUserRoleStructMapper {

    AuthUserRoleStructMapper INSTANCE = Mappers.getMapper(AuthUserRoleStructMapper.class);

}
