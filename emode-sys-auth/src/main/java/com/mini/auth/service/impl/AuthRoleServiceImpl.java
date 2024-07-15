package com.mini.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.auth.entity.AuthPermission;
import com.mini.auth.entity.AuthRole;
import com.mini.auth.entity.AuthRolePermission;
import com.mini.auth.mapper.AuthPermissionMapper;
import com.mini.auth.mapper.AuthRoleMapper;
import com.mini.auth.mapper.AuthRolePermissionMapper;
import com.mini.auth.mapperstruct.AuthRoleStructMapper;
import com.mini.auth.model.dto.AuthRoleRelationDTO;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.service.IAuthRoleService;
import com.mini.common.constant.LastSql;
import com.mini.common.enums.number.Delete;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.webmvc.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhl
 * @create 2024/6/3 15:45
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRoleServiceImpl implements IAuthRoleService {

    private final AuthRoleMapper authRoleMapper;

    private final AuthPermissionMapper authPermissionMapper;

    private final AuthRolePermissionMapper authRolePermissionMapper;

    @Override
    public IPage<AuthRoleRelationDTO> pageAuthRelation(AuthRoleQuery authRoleQuery) {
        return authRoleMapper.selectPage(authRoleQuery, authRoleQuery.build());
    }

    @Override
    public AuthRoleRelationDTO getRoleById(long id) {
        if (id <= 0) {
            throw new EModeServiceException("主键id有误");
        }
        return authRoleMapper.getRoleById(id);
    }

    @Override
    public void insert(AuthRoleRelationDTO dto) {
        // 校验是否重复
        checkExist(dto);

        // 校验权限集合
        List<Long> authPermissionIdList = dto.getAuthPermissionIdList();
        if (CollectionUtils.isEmpty(authPermissionIdList)) {
            throw new EModeServiceException("角色至少需要一个权限，请重新选择");
        }

        // 校验权限是否与当前符合
        checkExistPermission(authPermissionIdList);

        // 入库角色
        long roleId = IDGenerator.next();
        AuthRole authRoleDb = AuthRoleStructMapper.INSTANCE.dto2Entity(dto);
        authRoleDb.setId(roleId);
        authRoleDb.setDelFlag(Delete.NO);
        int b1 = authRoleMapper.insert(authRoleDb);

        if (b1 <= 0) {
            throw new EModeServiceException("角色入库失败");
        }

        // 入库角色和权限关联信息
        // 根据角色id和权限id构建关联关系list
        List<AuthRolePermission> authRolePermissionList = getAuthRolePermissionList(authPermissionIdList, roleId);

        int b2 = authRolePermissionMapper.batchInsert(authRolePermissionList);

        if (b2 <= 0) {
            throw new EModeServiceException("角色和权限关联入库失败");
        }
    }

    @Override
    public void del(long id) {
        if (id <= 0) {
            throw new EModeServiceException("主键id有误");
        }

        // 获取角色信息
        AuthRole authRole = getAuthRole(id);

        if (Objects.isNull(authRole)) {
            throw new EModeServiceException("删除信息不存在");
        }

        // 根据角色id获取 关联信息
        List<AuthRolePermission> authRolePermissionList = getAuthRolePermissionList(id);

        // 删除角色信息
        authRole.setDelFlag(Delete.YES);
        int b = authRoleMapper.updateById(authRole);
        if (b <= 0) {
            throw new EModeServiceException("角色信息删除失败");
        }

        // 关联信息
        if (CollectionUtils.isNotEmpty(authRolePermissionList)) {
            authRolePermissionList.forEach(e -> e.setDelFlag(Delete.YES));
            int b1 = authRolePermissionMapper.batchUpdate(authRolePermissionList);
            if (b1 <= 0) {
                throw new EModeServiceException("角色和权限关联信息删除失败");
            }
        }
    }


    @Override
    public void update(AuthRoleRelationDTO dto) {
        Long roleId = dto.getId();
        List<Long> permissionIdList = dto.getAuthPermissionIdList();

        AuthRole authRole = getAuthRole(roleId);
        if (Objects.isNull(authRole)) {
            throw new EModeServiceException("修改信息不存在");
        }

        // 校验权限
        checkExistPermission(permissionIdList);

        // 前端传入最新数据，之前数据删除，插入最新数据
        List<AuthRolePermission> authRolePermissionList = getAuthRolePermissionList(roleId);
        authRolePermissionList.forEach(e -> e.setDelFlag(Delete.YES));
        if (CollectionUtils.isNotEmpty(authRolePermissionList)) {
            int b = authRolePermissionMapper.batchUpdate(authRolePermissionList);
            if (b <= 0) {
                throw new EModeServiceException("更新角色权限关联信息失败");
            }
        }

        // 插入关联信息
        List<AuthRolePermission> authRolePermissionList1 = getAuthRolePermissionList(permissionIdList, roleId);

        if (CollectionUtils.isNotEmpty(authRolePermissionList1)) {
            int b1 = authRolePermissionMapper.batchInsert(authRolePermissionList1);

            if (b1 <= 0) {
                throw new EModeServiceException("角色权限关联关系插入失败");
            }
        }

        // 更新角色信息
        AuthRole authRole1 = AuthRoleStructMapper.INSTANCE.dto2Entity(dto);
        int b2 = authRoleMapper.updateById(authRole1);

        if (b2 <= 0) {
            throw new EModeServiceException("角色信息更新失败");
        }
    }

    @Override
    public boolean checkRoleByRoleName(long id, String roleName) {
        LambdaQueryWrapper<AuthRole> wrapper = Wrappers.lambdaQuery(AuthRole.class);
        wrapper.eq(AuthRole::getRoleName, roleName)
                .eq(AuthRole::getDelFlag, Delete.NO);
        long count = authRoleMapper.selectCount(wrapper);

        // 更改完的名字，如果已经存在进行比较，不存在直接过
        if (count >= 1) {
            // 原数据
            AuthRole sourceRole = getAuthRole(id);

            if (Objects.nonNull(sourceRole)) {
                // true就是为原数据，false就是为新数据
                return Objects.equals(sourceRole.getRoleName(), roleName);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean checkRoleByRoleCode(long id, String roleCode) {
        LambdaQueryWrapper<AuthRole> wrapper = Wrappers.lambdaQuery(AuthRole.class);
        wrapper.eq(AuthRole::getRoleCode, roleCode)
                .eq(AuthRole::getDelFlag, Delete.NO);
        long count = authRoleMapper.selectCount(wrapper);

        // 更改完的，如果已经存在进行比较，不存在直接过
        if (count >= 1) {
            // 原数据
            AuthRole sourceRole = getAuthRole(id);

            if (Objects.nonNull(sourceRole)) {
                // true就是为原数据，false就是为新数据
                return Objects.equals(sourceRole.getRoleCode(), roleCode);
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 根据角色id和权限id构建关联关系list
     */
    private static List<AuthRolePermission> getAuthRolePermissionList(List<Long> permissionIdList, long roleId) {
        List<AuthRolePermission> authRolePermissionList = new ArrayList<>();
        permissionIdList.forEach(e -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setId(IDGenerator.next());
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(e);
            authRolePermission.setDelFlag(Delete.NO);
            authRolePermissionList.add(authRolePermission);
        });
        return authRolePermissionList;
    }

    /**
     * 根据角色id获取 关联信息
     */
    private List<AuthRolePermission> getAuthRolePermissionList(long id) {
        LambdaQueryWrapper<AuthRolePermission> wrapper1 = Wrappers.lambdaQuery(AuthRolePermission.class);
        wrapper1.eq(AuthRolePermission::getRoleId, id)
                .eq(AuthRolePermission::getDelFlag, Delete.NO);
        return authRolePermissionMapper.selectList(wrapper1);
    }

    /**
     * 根据角色id获取角色信息
     */
    private AuthRole getAuthRole(long id) {
        LambdaQueryWrapper<AuthRole> wrapper = Wrappers.lambdaQuery(AuthRole.class);
        wrapper.eq(AuthRole::getId, id)
                .eq(AuthRole::getDelFlag, Delete.NO)
                .last(LastSql.LIMIT_ONE);
        return authRoleMapper.selectOne(wrapper);
    }

    /**
     * 根据角色信息校验是否重复
     */
    private void checkExist(AuthRoleRelationDTO dto) {
        String roleName = dto.getRoleName();
        String roleCode = dto.getRoleCode();

        LambdaQueryWrapper<AuthRole> wrapper = Wrappers.lambdaQuery(AuthRole.class);
        wrapper.and(role -> role.eq(AuthRole::getRoleName, roleName).or().eq(AuthRole::getRoleCode, roleCode))
                .eq(AuthRole::getDelFlag, Delete.NO)
                .last(LastSql.LIMIT_ONE);
        AuthRole authRole = authRoleMapper.selectOne(wrapper);

        if (Objects.nonNull(authRole)) {
            throw new EModeServiceException("角色名或角色编码已存在");
        }
    }

    /**
     * 校验当前权限集合是否完全存在
     */
    private void checkExistPermission(List<Long> authPermissionIdList) {
        if (CollectionUtils.isEmpty(authPermissionIdList)) {
            return;
        }

        LambdaQueryWrapper<AuthPermission> wrapper = Wrappers.lambdaQuery(AuthPermission.class);
        wrapper.in(AuthPermission::getId, authPermissionIdList)
                .eq(AuthPermission::getDelFlag, Delete.NO);
        List<AuthPermission> authPermissionList = authPermissionMapper.selectList(wrapper);

        if (authPermissionList.size() != authPermissionIdList.size()) {
            throw new EModeServiceException("新增角色所属权限不存在");
        }
    }
}
