package com.mini.biz.mapperstruct;

import com.mini.auth.entity.AuthUser;
import com.mini.biz.request.auth.AuthUserRequest;
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

    AuthUser request2Entity(AuthUserRequest request);
}
