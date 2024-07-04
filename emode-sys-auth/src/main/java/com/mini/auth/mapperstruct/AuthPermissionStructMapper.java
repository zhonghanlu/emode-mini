package com.mini.auth.mapperstruct;

import com.mini.auth.entity.AuthPermission;
import com.mini.auth.model.dto.AuthPermissionDTO;
import com.mini.auth.model.edit.AuthPermissionEdit;
import com.mini.auth.model.request.AuthPermissionRequest;
import com.mini.auth.model.vo.AuthPermissionVo;
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
     * dto2entity
     */
    AuthPermission dto2Entity(AuthPermissionDTO dto);

    /**
     * dto2vo
     */
    AuthPermissionVo dto2Vo(AuthPermissionDTO authPermissionDTO);

    /**
     * req2dto
     */
    AuthPermissionDTO req2Dto(AuthPermissionRequest request);

    /**
     * edit2dto
     */
    AuthPermissionDTO edit2Dto(AuthPermissionEdit edit);
}
