package com.mini.auth.mapperstruct;

import com.mini.auth.entity.AuthRole;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.edit.AuthRoleRelationEdit;
import com.mini.auth.model.request.AuthRoleRelationRequest;
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

    /**
     * dto2vo
     */
    AuthRoleRelationVo dto2Vo(AuthRoleRelationDTO dto);

    /**
     * dto2entity
     */
    AuthRole dto2Entity(AuthRoleRelationDTO dto);

    /**
     * request2dto
     */
    AuthRoleRelationDTO req2Dto(AuthRoleRelationRequest request);

    /**
     * edit2dto
     */
    AuthRoleRelationDTO edit2Dto(AuthRoleRelationEdit edit);
}
