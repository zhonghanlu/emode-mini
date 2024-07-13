package com.mini.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.auth.entity.AuthUser;
import com.mini.auth.mapper.AuthUserMapper;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.service.IAuthUserService;
import com.mini.common.constant.LastSql;
import com.mini.common.enums.number.Delete;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.mybatis.CommonMybatisUtil;
import com.mini.common.utils.webmvc.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        boolean b = CommonMybatisUtil.isExistById(id, authUserMapper, AuthUser.class);
        if (!b) {
            throw new EModeServiceException("参数有误，数据不存在，id：" + id);
        }
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

        boolean b = CommonMybatisUtil.isExistById(id, authUserMapper, AuthUser.class);
        if (!b) {
            throw new EModeServiceException("数据不存在，id：" + id);
        }

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
