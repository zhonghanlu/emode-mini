package com.mini.auth.service;

/**
 * @author zhl
 * @create 2024/6/3 15:43
 */
public interface IAuthUserRoleService {
    /**
     * 根据角色码获取数量
     */
    long getCountByRoleId(long roleId);
}
