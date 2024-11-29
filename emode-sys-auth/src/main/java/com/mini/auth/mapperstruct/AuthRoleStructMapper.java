package com.mini.auth.mapperstruct;

import com.mini.auth.entity.AuthRole;
import com.mini.auth.model.dto.AuthRoleDTO;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.edit.AuthRoleRelationEdit;
import com.mini.auth.model.request.AuthRoleRelationRequest;
import com.mini.auth.model.vo.AuthRoleDetailVo;
import com.mini.auth.model.vo.AuthRoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    AuthRoleVo dto2Vo(AuthRoleRelationDTO dto);

    /**
     * dto2Detailvo
     */
    @Mapping(source = "authPermissionDTOList", target = "authPermissionVoList")
    AuthRoleDetailVo dto2DetailVo(AuthRoleRelationDTO dto);

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

    /**
     * entity2dto
     */
    AuthRoleDTO entity2Dto(AuthRole authRole);

    /**
     * entityList2dtoList
     */
    List<AuthRoleDTO> entityList2DtoList(List<AuthRole> authRoleList);

    /**
     * dtoList2voList
     */
    List<AuthRoleVo> dtoList2VoList(List<AuthRoleDTO> all);
}
