package com.mini.auth.mapperstruct;

import com.mini.auth.entity.AuthUser;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.dto.AuthUserDetailDTO;
import com.mini.auth.model.edit.AuthUserEdit;
import com.mini.auth.model.request.AuthRegisterRequest;
import com.mini.auth.model.request.AuthUserRequest;
import com.mini.auth.model.vo.AuthUserDetailVo;
import com.mini.auth.model.vo.AuthUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/5/14 16:41
 */
@Mapper
public interface AuthUserStructMapper {

    AuthUserStructMapper INSTANCE = Mappers.getMapper(AuthUserStructMapper.class);

    /**
     * request2dto
     */
    AuthUserDTO request2Dto(AuthUserRequest request);

    /**
     * edit2dto
     */
    AuthUserDTO edit2Dto(AuthUserEdit edit);

    /**
     * dto2entity
     */
    AuthUser dto2Entity(AuthUserDTO dto);

    /**
     * dto2vo
     */
    AuthUserVo dto2Vo(AuthUserDTO dto);

    /**
     * detail2vo
     */
//    @Mapping(source = "authRoleDTOList", target = "authRoleVoList")
//    @Mapping(source = "authPermissionDTOList", target = "authPermissionVoList")
    AuthUserDetailVo dtoDetail2Vo(AuthUserDetailDTO authUserDetailDTO);

    /**
     * entity2dto
     */
    AuthUserDTO entity2Dto(AuthUser authUser);

    /**
     * register2dto
     */
    AuthUserDTO reqRegister2Dto(AuthRegisterRequest request);
}
