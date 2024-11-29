package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthPermissionStructMapper;
import com.mini.auth.model.dto.AuthPermissionDTO;
import com.mini.auth.model.edit.AuthPermissionEdit;
import com.mini.auth.model.query.AuthPermissionQuery;
import com.mini.auth.model.request.AuthPermissionRequest;
import com.mini.auth.model.vo.AuthPermissionVo;
import com.mini.auth.service.IAuthPermissionService;
import com.mini.common.utils.TreeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhl
 * @create 2024/7/4 16:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysPermissionBiz {

    private final IAuthPermissionService authPermissionService;

    /**
     * 新增权限
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(AuthPermissionRequest request) {
        AuthPermissionDTO dto = AuthPermissionStructMapper.INSTANCE.req2Dto(request);
        authPermissionService.insert(dto);
    }

    /**
     * 删除权限
     */
    @Transactional(rollbackFor = Exception.class)
    public void del(Long id) {
        authPermissionService.del(id);
    }

    /**
     * 更新权限
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(AuthPermissionEdit edit) {
        AuthPermissionDTO dto = AuthPermissionStructMapper.INSTANCE.edit2Dto(edit);
        authPermissionService.update(dto);
    }

    /**
     * 权限分页
     */
    public IPage<AuthPermissionVo> page(AuthPermissionQuery query) {
        IPage<AuthPermissionDTO> dtoIPage = authPermissionService.pagePermission(query);
        IPage<AuthPermissionVo> voIPage = dtoIPage.convert(AuthPermissionStructMapper.INSTANCE::dto2Vo);
        return TreeUtils.buildByPage(voIPage);
    }

    /**
     * 所有权限
     */
    public List<AuthPermissionVo> all() {
        return TreeUtils.build(AuthPermissionStructMapper.INSTANCE.dtoList2VoList(authPermissionService.selectAll()));
    }
}
