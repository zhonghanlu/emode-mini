package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthRoleStructMapper;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.edit.AuthRoleRelationEdit;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.model.request.AuthRoleRelationRequest;
import com.mini.auth.model.vo.AuthRoleDetailVo;
import com.mini.auth.model.vo.AuthRoleVo;
import com.mini.auth.service.IAuthRoleService;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.TreeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author zhl
 * @create 2024/6/4 17:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysRoleBiz {

    private final IAuthRoleService authRoleService;


    /**
     * 角色关联分页
     */
    public IPage<AuthRoleVo> page(AuthRoleQuery query) {
        IPage<AuthRoleRelationDTO> pagedAuthRelation = authRoleService.pageAuthRelation(query);
        return pagedAuthRelation.convert(AuthRoleStructMapper.INSTANCE::dto2Vo);
    }


    /**
     * 根据角色id查询角色详细权限信息
     */
    public AuthRoleDetailVo getRoleById(long id) {
        AuthRoleDetailVo authRoleDetailVo = AuthRoleStructMapper.INSTANCE.dto2DetailVo(authRoleService.getRoleById(id));
        if (Objects.nonNull(authRoleDetailVo) && Objects.nonNull(authRoleDetailVo.getAuthPermissionVoList())) {
            authRoleDetailVo.setAuthPermissionVoList(TreeUtils.build(authRoleDetailVo.getAuthPermissionVoList()));
        }
        return authRoleDetailVo;
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

        if (!authRoleService.checkRoleByRoleCode(dto.getId(), dto.getRoleCode()) ||
                !authRoleService.checkRoleByRoleName(dto.getId(), dto.getRoleName())) {
            throw new EModeServiceException("更改名字或编码重复");
        }

        authRoleService.update(dto);
    }

}
