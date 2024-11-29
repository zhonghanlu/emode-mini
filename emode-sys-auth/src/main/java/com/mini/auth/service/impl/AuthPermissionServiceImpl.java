package com.mini.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.auth.entity.AuthPermission;
import com.mini.auth.mapper.AuthPermissionMapper;
import com.mini.auth.mapperstruct.AuthPermissionStructMapper;
import com.mini.auth.model.dto.AuthPermissionDTO;
import com.mini.auth.model.query.AuthPermissionQuery;
import com.mini.auth.service.IAuthPermissionService;
import com.mini.common.constant.ErrorCodeConstant;
import com.mini.common.constant.LastSql;
import com.mini.common.enums.number.Delete;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.webmvc.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author zhl
 * @create 2024/6/3 15:44
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthPermissionServiceImpl implements IAuthPermissionService {

    private final AuthPermissionMapper authPermissionMapper;


    @Override
    public void insert(AuthPermissionDTO dto) {
        AuthPermission authPermission = AuthPermissionStructMapper.INSTANCE.dto2Entity(dto);

        // 菜单名重复直接返回
        LambdaQueryWrapper<AuthPermission> wrapper = Wrappers.lambdaQuery(AuthPermission.class);
        wrapper.eq(AuthPermission::getMenuName, authPermission.getMenuName())
                .last(LastSql.LIMIT_ONE);
        AuthPermission permission = authPermissionMapper.selectOne(wrapper);
        if (Objects.nonNull(permission)) {
            throw new EModeServiceException(ErrorCodeConstant.BUSINESS_ERROR, "菜单名重复，请重新输入");
        }

        authPermission.setId(IDGenerator.next());
        authPermission.setDelFlag(Delete.NO);

        int b = authPermissionMapper.insert(authPermission);

        if (b <= 0) {
            throw new EModeServiceException(ErrorCodeConstant.DB_ERROR, "权限新增失败");
        }
    }

    @Override
    public void del(long id) {

        if (id <= 0) {
            throw new EModeServiceException(ErrorCodeConstant.PARAM_ERROR, "主键id有误，id:" + id);
        }

        AuthPermission authPermission = getAuthPermission(id);

        if (Objects.isNull(authPermission)) {
            throw new EModeServiceException(ErrorCodeConstant.BUSINESS_ERROR, "权限信息不存在，id:" + id);
        }

        // 1.判断是否为父级菜单
        if (0 == authPermission.getParentId()) {
            // 2.查看下面是否有子节点
            List<AuthPermission> authPermissionList = getChildrenElement(id);
            if (CollectionUtils.isNotEmpty(authPermissionList)) {
                throw new EModeServiceException(ErrorCodeConstant.BUSINESS_ERROR, "当前节点下有子节点，不允许删除");
            }
        }

        authPermission.setDelFlag(Delete.YES);
        int b = authPermissionMapper.updateById(authPermission);
        if (b <= 0) {
            throw new EModeServiceException(ErrorCodeConstant.DB_ERROR, "权限删除失败，id：" + id);
        }
    }


    @Override
    public void update(AuthPermissionDTO dto) {
        AuthPermission authPermission = AuthPermissionStructMapper.INSTANCE.dto2Entity(dto);
        Long id = authPermission.getId();
        if (id <= 0) {
            throw new EModeServiceException(ErrorCodeConstant.PARAM_ERROR, "主键id有误，id:" + id);
        }

        AuthPermission authPermission1 = getAuthPermission(id);
        if (Objects.isNull(authPermission1)) {
            throw new EModeServiceException(ErrorCodeConstant.BUSINESS_ERROR, "权限信息不存在，id:" + id);
        }

        int b = authPermissionMapper.updateById(authPermission);

        if (b <= 0) {
            throw new EModeServiceException(ErrorCodeConstant.DB_ERROR, "权限信息更新异常");
        }
    }

    @Override
    public IPage<AuthPermissionDTO> pagePermission(AuthPermissionQuery query) {
        IPage<AuthPermissionDTO> page = query.build();
        return authPermissionMapper.selectPage(query, page);
    }

    @Override
    public List<AuthPermissionDTO> selectAll() {
        LambdaQueryWrapper<AuthPermission> wrapper = Wrappers.lambdaQuery(AuthPermission.class);
        wrapper.eq(AuthPermission::getDelFlag, Delete.NO);
        return AuthPermissionStructMapper.INSTANCE.entityList2DtoList(authPermissionMapper.selectList(wrapper));
    }

    /**
     * 获取当前节点下的子节点
     */
    private List<AuthPermission> getChildrenElement(long id) {
        LambdaQueryWrapper<AuthPermission> wrapper = Wrappers.lambdaQuery(AuthPermission.class);
        wrapper.eq(AuthPermission::getParentId, id)
                .eq(AuthPermission::getDelFlag, Delete.NO);
        return authPermissionMapper.selectList(wrapper);
    }

    /**
     * 根据主键id查询权限
     */
    private AuthPermission getAuthPermission(long id) {
        LambdaQueryWrapper<AuthPermission> wrapper = Wrappers.lambdaQuery(AuthPermission.class);
        wrapper.eq(AuthPermission::getId, id)
                .eq(AuthPermission::getDelFlag, Delete.NO)
                .last(LastSql.LIMIT_ONE);
        return authPermissionMapper.selectOne(wrapper);
    }
}
