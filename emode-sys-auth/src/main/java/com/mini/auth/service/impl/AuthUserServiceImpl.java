package com.mini.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.auth.entity.AuthRole;
import com.mini.auth.entity.AuthUser;
import com.mini.auth.entity.AuthUserRole;
import com.mini.auth.mapper.AuthRoleMapper;
import com.mini.auth.mapper.AuthUserMapper;
import com.mini.auth.mapper.AuthUserRoleMapper;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.dto.AuthUserDetailDTO;
import com.mini.auth.model.dto.AuthUserRoleDTO;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.service.IAuthUserService;
import com.mini.common.constant.LastSql;
import com.mini.common.enums.number.Delete;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.mybatis.CommonMybatisUtil;
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
 * @create 2024/5/14 15:40
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements IAuthUserService {

    private final AuthUserMapper authUserMapper;

    private final AuthRoleMapper authRoleMapper;

    private final AuthUserRoleMapper authUserRoleMapper;

    @Override
    public void insert(AuthUserDTO dto) {
        AuthUser authUser = AuthUserStructMapper.INSTANCE.dto2Entity(dto);

        // 校验用户名是否重复
        checkExistUsername(authUser.getUsername());

        authUser.setId(IDGenerator.next());
        int b = authUserMapper.insert(authUser);
        if (b <= 0) {
            throw new EModeServiceException("插入失败");
        }
    }

    @Override
    public void del(long id) {
        if (id <= 0) {
            throw new EModeServiceException("参数id有误，id:" + id);
        }

        checkUserExist(id);

        LambdaUpdateWrapper<AuthUser> updateWrapper = Wrappers.lambdaUpdate(AuthUser.class);
        updateWrapper.set(AuthUser::getDelFlag, Delete.YES)
                .eq(AuthUser::getId, id)
                .eq(AuthUser::getDelFlag, Delete.NO);
        int b1 = authUserMapper.update(updateWrapper);

        if (b1 <= 0) {
            throw new EModeServiceException("刪除错误");
        }
    }

    @Override
    public void update(AuthUserDTO dto) {
        AuthUser authUser = AuthUserStructMapper.INSTANCE.dto2Entity(dto);
        Long id = authUser.getId();
        if (Objects.isNull(id) || id <= 0) {
            throw new EModeServiceException("参数id有误，id:" + id);
        }

        checkUserExist(id);

        int b1 = authUserMapper.updateById(authUser);

        if (b1 <= 0) {
            throw new EModeServiceException("更新错误");
        }
    }

    @Override
    public IPage<AuthUserDTO> selectPage(AuthUserQuery query) {
        IPage<AuthUserDTO> page = query.build();
        return authUserMapper.selectPage(query, page);
    }

    @Override
    public void relationUserAndRole(AuthUserRoleDTO authUserRoleDTO) {
        List<Long> roleIdList = authUserRoleDTO.getRoleIdList();
        Long userId = authUserRoleDTO.getId();

        // 检查有角色id是否存在
        List<AuthRole> authRoleList = checkRoleIdExist(roleIdList);

        // 检查修改用户是否存在
        AuthUser authUser = CommonMybatisUtil.getById(userId, authUserMapper, AuthUser.class);
        if (Objects.isNull(authUser)) {
            throw new EModeServiceException("当前修改用户信息不存在");
        }

        // 入库,检测之前的角色数据，删除，插入新的
        LambdaQueryWrapper<AuthUserRole> wrapper1 = Wrappers.lambdaQuery(AuthUserRole.class);

        wrapper1.eq(AuthUserRole::getUserId, userId)
                .eq(AuthUserRole::getDelFlag, Delete.NO);

        List<AuthUserRole> authUserRoleList = authUserRoleMapper.selectList(wrapper1);

        // 简单粗暴，把之前的删除
        if (CollectionUtils.isNotEmpty(authUserRoleList)) {
            LambdaUpdateWrapper<AuthUserRole> wrapper2 = Wrappers.lambdaUpdate(AuthUserRole.class);
            wrapper2.eq(AuthUserRole::getUserId, userId)
                    .set(AuthUserRole::getDelFlag, Delete.YES);
            int b = authUserRoleMapper.update(wrapper2);
            if (b <= 0) {
                throw new EModeServiceException("角色用户关联失败");
            }
        }

        // 将最新的插入
        List<AuthUserRole> authUserRoleList1 = genderUserRoleList(authRoleList, authUser);

        if (CollectionUtils.isNotEmpty(authUserRoleList1)) {
            int b1 = authUserRoleMapper.batchInsert(authUserRoleList1);

            if (b1 <= 0) {
                throw new EModeServiceException("角色用户关联插入失败");
            }
        }
    }

    @Override
    public AuthUserDetailDTO getUserRolePermissionById(long id) {
        checkUserExist(id);
        return authUserMapper.getUserRolePermissionById(id);
    }

    @Override
    public AuthUserDetailDTO getUserRoleById(long id) {
        checkUserExist(id);
        return authUserMapper.getUserRoleById(id);
    }

    @Override
    public AuthUserDetailDTO getUserPermissionById(long id) {
        checkUserExist(id);
        return authUserMapper.getUserPermissionById(id);
    }

    @Override
    public AuthUserDetailDTO getUserById(long id) {
        checkUserExist(id);
        return authUserMapper.getUserById(id);
    }

    /**
     * 校验用户id数据是否存在
     */
    private void checkUserExist(long id) {
        boolean b = CommonMybatisUtil.isExistById(id, authUserMapper, AuthUser.class);
        if (!b) {
            throw new EModeServiceException("用户不存在,id:" + id);
        }
    }

    /**
     * 检查角色id是否存在
     */
    private List<AuthRole> checkRoleIdExist(List<Long> roleIdList) {
        List<AuthRole> authRoleList = new ArrayList<>(roleIdList.size());
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            LambdaQueryWrapper<AuthRole> wrapper = Wrappers.lambdaQuery(AuthRole.class);
            wrapper.in(AuthRole::getId, roleIdList)
                    .eq(AuthRole::getDelFlag, Delete.NO);
            authRoleList = authRoleMapper.selectList(wrapper);

            if (authRoleList.size() != roleIdList.size()) {
                throw new EModeServiceException("当前角色id部分不存在");
            }
        }
        return authRoleList;
    }

    /**
     * 生成list对象
     */
    private List<AuthUserRole> genderUserRoleList(List<AuthRole> authRoleList, AuthUser authUser) {
        List<AuthUserRole> authUserRoleList = new ArrayList<>(authRoleList.size());
        Long userId = authUser.getId();
        String username = authUser.getUsername();
        authRoleList.forEach(r -> {
            AuthUserRole authUserRole = new AuthUserRole();
            authUserRole.setId(IDGenerator.next());
            authUserRole.setRoleId(r.getId());
            authUserRole.setRoleName(r.getRoleName());
            authUserRole.setUserId(userId);
            authUserRole.setUsername(username);
            authUserRole.setDelFlag(Delete.NO);
            authUserRoleList.add(authUserRole);
        });
        return authUserRoleList;
    }

    /**
     * 校验用户名是否重复
     */
    private void checkExistUsername(String username) {
        LambdaQueryWrapper<AuthUser> wrapper = Wrappers.lambdaQuery(AuthUser.class);
        wrapper.eq(AuthUser::getUsername, username)
                .eq(AuthUser::getDelFlag, Delete.NO)
                .last(LastSql.LIMIT_ONE);
        AuthUser authUser1 = authUserMapper.selectOne(wrapper);
        if (Objects.nonNull(authUser1)) {
            throw new EModeServiceException("当前用户名已经注册");
        }
    }
}
