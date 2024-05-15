package com.mini.auth.service;

import com.mini.auth.model.dto.AuthUserDTO;

import java.util.List;

/**
 * @author zhl
 * @create 2024/5/14 15:40
 */
public interface IAuthUserService {

    /**
     * 插入
     */
    void insert(AuthUserDTO authUserDTO);

    /**
     * 删除
     */
    void del(long id);

    /**
     * 更新
     */
    void update(AuthUserDTO authUserDTO);

    /**
     * 查分页
     */
    List<AuthUserDTO> selectPage();

}
