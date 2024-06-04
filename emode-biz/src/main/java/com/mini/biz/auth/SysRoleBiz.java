package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthRoleStructMapper;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.edit.AuthRoleRelationEdit;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.model.request.AuthRoleRelationRequest;
import com.mini.auth.model.vo.AuthRoleRelationVo;
import com.mini.auth.service.IAuthRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
