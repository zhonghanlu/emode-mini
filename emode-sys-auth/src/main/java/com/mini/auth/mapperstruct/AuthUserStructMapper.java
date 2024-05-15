package com.mini.auth.mapperstruct;

import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.entity.AuthUser;
import com.mini.auth.model.request.AuthUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/5/14 16:41
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthUserStructMapper {

    AuthUserStructMapper INSTANCE = Mappers.getMapper(AuthUserStructMapper.class);

    AuthUserDTO request2dto(AuthUserRequest request);

    AuthUser dto2Entity(AuthUserDTO dto);
}
