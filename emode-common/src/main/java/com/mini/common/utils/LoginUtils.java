package com.mini.common.utils;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mini.common.constant.UserConstant;
import com.mini.common.enums.str.Device;
import com.mini.common.enums.str.UserType;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.model.LoginUser;
import org.apache.commons.lang3.StringUtils;

/**
 * 登录鉴权工具
 * 为适配多端登录而封装
 *
 * @author zhl
 * @source_auth ruoyi-plus
 */
public class LoginUtils {

    private LoginUtils() {
    }

    private static final String LOGIN_USER_KEY = "loginUser";

    /**
     * 登录系统
     * 针对两套用户体系
     *
     * @param loginUser 登录用户信息
     */
    public static void login(LoginUser loginUser, UserType userType) {
        StpUtil.login(userType.getStringValue() + loginUser.getUserId());
        setLoginUser(loginUser);
    }

    /**
     * 登录系统 基于 设备类型
     * 针对一套用户体系
     * <p>
     * 设备端目前只有pc和小程序（手机端）
     *
     * @param loginUser 登录用户信息
     */
    public static void loginByDevice(LoginUser loginUser, UserType userType, Device deviceType) {
        StpUtil.login(userType.getStringValue() + loginUser.getUserId(), deviceType.getStringValue());
        setLoginUser(loginUser);
    }

    /**
     * 设置用户数据
     */
    public static void setLoginUser(LoginUser loginUser) {
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        return (LoginUser) StpUtil.getTokenSession().get(LOGIN_USER_KEY);
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        LoginUser loginUser = getLoginUser();
        if (ObjectUtil.isNull(loginUser)) {
            String loginId = StpUtil.getLoginIdAsString();
            String userId;
            String replace = "";
            if (StringUtils.contains(loginId, UserType.MINI.getStringValue())) {
                userId = StringUtils.replace(loginId, UserType.MINI.getStringValue(), replace);
            } else if (StringUtils.contains(loginId, UserType.PC.getStringValue())) {
                userId = StringUtils.replace(loginId, UserType.PC.getStringValue(), replace);
            } else if (StringUtils.contains(loginId, UserType.MANAGER.getStringValue())) {
                userId = StringUtils.replace(loginId, UserType.MANAGER.getStringValue(), replace);
            } else {
                throw new EModeServiceException("登录用户: LoginId异常 => " + loginId);
            }
            return Long.parseLong(userId);
        }
        return loginUser.getUserId();
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户类型
     */
    public static UserType getUserType() {
        String loginId = StpUtil.getLoginIdAsString();
        return getUserType(loginId);
    }

    /**
     * 判断当前id是否为超管
     */
    public static boolean isSuperAdmin(long userId) {
        return UserConstant.SUPER_ADMIN_ID.equals(userId);
    }


    public static UserType getUserType(Object loginId) {
        if (StringUtils.contains(loginId.toString(), UserType.PC.getStringValue())) {
            return UserType.PC;
        } else if (StringUtils.contains(loginId.toString(), UserType.MINI.getStringValue())) {
            return UserType.MINI;
        } else if (StringUtils.contains(loginId.toString(), UserType.MANAGER.getStringValue())) {
            return UserType.MANAGER;
        } else {
            throw new EModeServiceException("登录用户: LoginId异常 => " + loginId);
        }
    }

}