package com.mini.auth.mapperstruct;

import com.mini.auth.model.dto.AuthUserRoleDTO;
import com.mini.auth.model.request.AuthUserRoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/6/3 15:39
 */
@Mapper
public interface AuthUserRoleStructMapper {

    AuthUserRoleStructMapper INSTANCE = Mappers.getMapper(AuthUserRoleStructMapper.class);

    /**
     * req2dto
     */
    AuthUserRoleDTO req2Dto(AuthUserRoleRequest request);
}
