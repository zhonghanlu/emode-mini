package com.mini.auth.mapperstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/6/3 15:38
 */
@Mapper
public interface AuthPermissionStructMapper {
    AuthPermissionStructMapper INSTANCE = Mappers.getMapper(AuthPermissionStructMapper.class);
}
