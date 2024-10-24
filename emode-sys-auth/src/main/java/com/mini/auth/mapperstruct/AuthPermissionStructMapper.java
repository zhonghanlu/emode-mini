package com.mini.auth.mapperstruct;

import com.mini.auth.entity.AuthPermission;
import com.mini.auth.model.dto.AuthPermissionDTO;
import com.mini.auth.model.edit.AuthPermissionEdit;
import com.mini.auth.model.request.AuthPermissionRequest;
import com.mini.auth.model.vo.AuthPermissionVo;
import com.mini.auth.model.vo.AuthUserDetailRouterVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhl
 * @create 2024/6/3 15:38
 */
@Mapper
public interface AuthPermissionStructMapper {
    AuthPermissionStructMapper INSTANCE = Mappers.getMapper(AuthPermissionStructMapper.class);

    /**
     * dto2entity List
     */
    List<AuthPermission> dtoList2EntityList(List<AuthPermissionDTO> permissionList);

    /**
     * entity2dto List
     */
    List<AuthPermissionDTO> entityList2DtoList(List<AuthPermission> permissionList);

    /**
     * dto2entity
     */
    AuthPermission dto2Entity(AuthPermissionDTO dto);

    /**
     * dto2vo
     */
    AuthPermissionVo dto2Vo(AuthPermissionDTO authPermissionDTO);

    /**
     * dtoList2VoList
     */
    List<AuthPermissionVo> dtoList2VoList(List<AuthPermissionDTO> authPermissionDTOList);

    /**
     * req2dto
     */
    AuthPermissionDTO req2Dto(AuthPermissionRequest request);

    /**
     * edit2dto
     */
    AuthPermissionDTO edit2Dto(AuthPermissionEdit edit);

    /**
     * dto2routerVo
     */
    List<AuthUserDetailRouterVo> dtoList2RouterVoList(List<AuthPermissionDTO> authPermissionDTOList);
}
