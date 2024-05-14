package com.mini.auth.service;

import com.mini.auth.entity.AuthUser;

import java.util.List;

/**
 * @author zhl
 * @create 2024/5/14 15:40
 */
public interface IAuthUserService {

    void insert(AuthUser authUser);

    void del(long id);

    void update(AuthUser authUser);

    List<AuthUser> selectPage();

}
