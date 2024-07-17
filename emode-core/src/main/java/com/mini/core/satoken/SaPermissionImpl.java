package com.mini.core.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.mini.common.enums.str.UserType;
import com.mini.common.model.LoginUser;
import com.mini.common.utils.LoginUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhl
 * sa-token 权限管理实现类
 */
public class SaPermissionImpl implements StpInterface {

    /**
     * 获取菜单权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginUser loginUser = LoginUtils.getLoginUser();
        UserType userType = UserType.get(loginUser.getUserType().getStringValue());
        if (userType == UserType.MANAGER) {
            return new ArrayList<>(loginUser.getMenuPermission());
        } else if (userType == UserType.PC) {
            // TODO pc客户端的权限
        } else if (userType == UserType.MINI) {
            // TODO 小程序端权限
        }
        return new ArrayList<>();
    }

    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginUtils.getLoginUser();
        UserType userType = UserType.get(loginUser.getUserType().getStringValue());
        if (userType == UserType.MANAGER) {
            return new ArrayList<>(loginUser.getRolePermission());
        } else if (userType == UserType.PC) {
            // TODO pc 端角色
        } else if (userType == UserType.MINI) {
            // TODO  小程序端角色
        }
        return new ArrayList<>();
    }
}
