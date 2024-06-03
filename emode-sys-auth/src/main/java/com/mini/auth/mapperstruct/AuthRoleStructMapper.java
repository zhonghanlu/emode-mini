package com.mini.auth.mapperstruct;

import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.vo.AuthRoleRelationVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/6/3 15:39
 */
@Mapper
public interface AuthRoleStructMapper {

    AuthRoleStructMapper INSTANCE = Mappers.getMapper(AuthRoleStructMapper.class);

    AuthRoleRelationVo dto2Vo(AuthRoleRelationDTO dto);

}
