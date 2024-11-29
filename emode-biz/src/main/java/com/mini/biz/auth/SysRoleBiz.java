package com.mini.biz.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthRoleStructMapper;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.edit.AuthRoleRelationEdit;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.model.request.AuthRoleRelationRequest;
import com.mini.auth.model.vo.AuthRoleDetailVo;
import com.mini.auth.model.vo.AuthRoleVo;
import com.mini.auth.service.IAuthRoleService;
import com.mini.auth.service.IAuthUserRoleService;
import com.mini.common.constant.ErrorCodeConstant;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.TreeUtils;
import com.mini.common.utils.str.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhl
 * @create 2024/6/4 17:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysRoleBiz {

    private final IAuthRoleService authRoleService;

    private final IAuthUserRoleService authUserRoleService;

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
            throw new EModeServiceException(ErrorCodeConstant.BUSINESS_ERROR, "更改名字或编码重复");
        }

        authRoleService.update(dto);

        // 处理在线用户的 此角色的 token 问题，直接退出
        handlerOnlineUserRoleById(dto.getId(), dto.getRoleCode());
    }

    /**
     * 处理在线用户的角色权限
     */
    private void handlerOnlineUserRoleById(long roleId, String roleCode) {
        long num = authUserRoleService.getCountByRoleId(roleId);
        if (num <= 0) {
            return;
        }
        List<String> sessionIdList = StpUtil.searchSessionId("", 0, -1, false);

        if (CollectionUtils.isEmpty(sessionIdList)) {
            return;
        }

        List<String> loginIdList = sessionIdList.stream()
                .map(s -> StrUtil.getPartAfterLastDelimiter(s, ":"))
                .collect(Collectors.toList());

        loginIdList.parallelStream().forEach(t -> {
            if (StpUtil.hasRole(t, roleCode)) {
                StpUtil.logoutByTokenValue(t);
            }
        });

    }

    /**
     * 所有角色
     */
    public List<AuthRoleVo> all() {
        return AuthRoleStructMapper.INSTANCE.dtoList2VoList(authRoleService.all());
    }
}
