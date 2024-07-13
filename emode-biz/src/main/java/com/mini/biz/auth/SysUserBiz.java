package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthRoleStructMapper;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.edit.AuthRoleRelationEdit;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.model.request.AuthRoleRelationRequest;
import com.mini.auth.model.vo.AuthRoleRelationVo;
import com.mini.auth.model.vo.AuthUserVo;
import com.mini.auth.service.IAuthRoleService;
import com.mini.auth.service.IAuthUserService;
import com.mini.common.utils.SmCryptoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhl
 * @create 2024/5/14 16:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysUserBiz {

    private final IAuthUserService authUserService;

    private final IAuthRoleService authRoleService;

    @Transactional(rollbackFor = Exception.class)
    public void add(AuthUserDTO dto) {
        // 前端传入 sm2 的加密密文
        String password = SmCryptoUtil.doSm2Decrypt(dto.getPassword());
        // 对密码进行解密做 hash
        dto.setPassword(SmCryptoUtil.doHashValue(password));
        authUserService.insert(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(AuthUserDTO dto) {
        authUserService.update(dto);
    }

    public IPage<AuthUserVo> page(AuthUserQuery query) {
        IPage<AuthUserDTO> authUserDTOIPage = authUserService.selectPage(query);
        return authUserDTOIPage.convert(AuthUserStructMapper.INSTANCE::dto2Vo);
    }

    /**
     * 角色关联分页
     */
    public IPage<AuthRoleRelationVo> page(AuthRoleQuery query) {
        IPage<AuthRoleRelationDTO> pagedAuthRelation = authRoleService.pageAuthRelation(query);
        return pagedAuthRelation.convert(AuthRoleStructMapper.INSTANCE::dto2Vo);
    }


    /**
     * 根据角色id查询角色详细权限信息
     */
    public AuthRoleRelationVo getRoleById(long id) {
        return AuthRoleStructMapper.INSTANCE.dto2Vo(authRoleService.getRoleById(id));
    }

    /**
     * 新增角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(AuthRoleRelationRequest request) {
        AuthRoleRelationDTO relationDTO = AuthRoleStructMapper.INSTANCE.req2Dto(request);
        authRoleService.insert(relationDTO);
    }

    /**
     * 删除角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void del(long id) {
        authRoleService.del(id);
    }

    /**
     * 修改角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(AuthRoleRelationEdit edit) {
        AuthRoleRelationDTO dto = AuthRoleStructMapper.INSTANCE.edit2Dto(edit);
        authRoleService.update(dto);
    }

}
