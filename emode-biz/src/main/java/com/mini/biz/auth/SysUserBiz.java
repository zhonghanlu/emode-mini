package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthUserRoleStructMapper;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.dto.AuthUserDetailDTO;
import com.mini.auth.model.dto.AuthUserRoleDTO;
import com.mini.auth.model.edit.AuthUserEdit;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.model.request.AuthUserRequest;
import com.mini.auth.model.request.AuthUserRoleRequest;
import com.mini.auth.model.vo.AuthPermissionVo;
import com.mini.auth.model.vo.AuthUserDetailVo;
import com.mini.auth.model.vo.AuthUserVo;
import com.mini.auth.service.IAuthRoleService;
import com.mini.auth.service.IAuthUserService;
import com.mini.common.enums.str.UserQueryType;
import com.mini.common.utils.SmCryptoUtil;
import com.mini.common.utils.TreeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhl
 * @create 2024/5/14 16:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysUserBiz {

    private final IAuthUserService authUserService;

    /**
     * 新增用户
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(AuthUserRequest request) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.request2Dto(request);
        // 前端传入 sm2 的加密密文
        String password = SmCryptoUtil.doSm2Decrypt(authUserDTO.getPassword());
        // 对密码进行解密做 hash
        authUserDTO.setPassword(SmCryptoUtil.doHashValue(password));
        authUserService.insert(authUserDTO);
    }

    /**
     * 修改用户
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(AuthUserEdit edit) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.edit2Dto(edit);
        authUserService.update(authUserDTO);
    }

    /**
     * 分页查询
     */
    public IPage<AuthUserVo> page(AuthUserQuery query) {
        IPage<AuthUserDTO> authUserDTOIPage = authUserService.selectPage(query);
        return authUserDTOIPage.convert(AuthUserStructMapper.INSTANCE::dto2Vo);
    }

    /**
     * 用户角色关联关系
     */
    @Transactional(rollbackFor = Exception.class)
    public void relationUserRole(AuthUserRoleRequest request) {
        AuthUserRoleDTO authUserRoleDTO = AuthUserRoleStructMapper.INSTANCE.req2Dto(request);
        authUserService.relationUserAndRole(authUserRoleDTO);
    }

    /**
     * 根据查询类型查询用户详细信息
     */
    public AuthUserDetailVo getUserRolePermissionById(long id, UserQueryType type) {
        AuthUserDetailDTO authUserDetailDTO = new AuthUserDetailDTO();
        switch (type) {
            case ALL:
                authUserDetailDTO = authUserService.getUserRolePermissionById(id);
                break;
            case ROLE:
                authUserDetailDTO = authUserService.getUserRoleById(id);
                break;
            case PERMISSION:
                authUserDetailDTO = authUserService.getUserPermissionById(id);
                break;
            case BASE:
                authUserDetailDTO = authUserService.getUserById(id);
                break;
            default:
                break;
        }
        AuthUserDetailVo authUserDetailVo = AuthUserStructMapper.INSTANCE.dtoDetail2Vo(authUserDetailDTO);
        List<AuthPermissionVo> authPermissionVoList = authUserDetailVo.getAuthPermissionVoList();
        if (CollectionUtils.isNotEmpty(authPermissionVoList)) {
            authUserDetailVo.setAuthPermissionVoList(TreeUtils.build(authPermissionVoList));
        }
        return authUserDetailVo;
    }
}
