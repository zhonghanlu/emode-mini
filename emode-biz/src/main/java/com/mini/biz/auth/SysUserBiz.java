package com.mini.biz.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthUserRoleStructMapper;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.*;
import com.mini.auth.model.edit.AuthUserEdit;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.model.request.AuthLoginRequest;
import com.mini.auth.model.request.AuthUserRequest;
import com.mini.auth.model.request.AuthUserRoleRequest;
import com.mini.auth.model.vo.AuthPermissionVo;
import com.mini.auth.model.vo.AuthUserDetailVo;
import com.mini.auth.model.vo.AuthUserVo;
import com.mini.auth.service.IAuthUserService;
import com.mini.common.constant.RedisConstant;
import com.mini.common.enums.str.UserQueryType;
import com.mini.common.enums.str.UserType;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.model.LoginModel;
import com.mini.common.model.LoginUser;
import com.mini.common.utils.LoginUtils;
import com.mini.common.utils.SmCryptoUtil;
import com.mini.common.utils.TreeUtils;
import com.mini.common.utils.redis.RedisUtils;
import com.mini.core.config.properties.CaptchaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author zhl
 * @create 2024/5/14 16:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysUserBiz {

    private final IAuthUserService authUserService;

    private final CaptchaProperties captchaProperties;

    /**
     * 新增用户
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(AuthUserRequest request) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.request2Dto(request);
        // 前端传入 sm2 的加密密文
        String password = SmCryptoUtil.doSm2Decrypt(authUserDTO.getPassword());
        // 对密码进行解密做 hash
        authUserDTO.setPassword(SmCryptoUtil.doHashValue(password));
        authUserService.insert(authUserDTO);
    }

    /**
     * 修改用户
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(AuthUserEdit edit) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.edit2Dto(edit);
        authUserService.update(authUserDTO);
    }

    /**
     * 分页查询
     */
    public IPage<AuthUserVo> page(AuthUserQuery query) {
        IPage<AuthUserDTO> authUserDTOIPage = authUserService.selectPage(query);
        return authUserDTOIPage.convert(AuthUserStructMapper.INSTANCE::dto2Vo);
    }

    /**
     * 用户角色关联关系
     */
    @Transactional(rollbackFor = Exception.class)
    public void relationUserRole(AuthUserRoleRequest request) {
        AuthUserRoleDTO authUserRoleDTO = AuthUserRoleStructMapper.INSTANCE.req2Dto(request);
        authUserService.relationUserAndRole(authUserRoleDTO);
    }

    /**
     * 根据查询类型查询用户详细信息
     */
    public AuthUserDetailVo getUserRolePermissionById(long id, UserQueryType type) {
        AuthUserDetailDTO authUserDetailDTO = new AuthUserDetailDTO();
        switch (type) {
            case ALL:
                authUserDetailDTO = authUserService.getUserRolePermissionById(id);
                break;
            case ROLE:
                authUserDetailDTO = authUserService.getUserRoleById(id);
                break;
            case PERMISSION:
                authUserDetailDTO = authUserService.getUserPermissionById(id);
                break;
            case BASE:
                authUserDetailDTO = authUserService.getUserById(id);
                break;
            default:
                break;
        }
        AuthUserDetailVo authUserDetailVo = AuthUserStructMapper.INSTANCE.dtoDetail2Vo(authUserDetailDTO);
        List<AuthPermissionVo> authPermissionVoList = authUserDetailVo.getAuthPermissionVoList();
        if (CollectionUtils.isNotEmpty(authPermissionVoList)) {
            authUserDetailVo.setAuthPermissionVoList(TreeUtils.build(authPermissionVoList));
        }
        return authUserDetailVo;
    }

    /**
     * 登录,三端登录汇总
     */
    public LoginModel login(AuthLoginRequest request) {

        if (UserType.MINI.equals(request.getUserType())) {
            throw new EModeServiceException("请键入PC端类型");
        }

        // 校验验证码
        if (captchaProperties.isEnabled()) {
            checkCaptcha(request.getCode(), request.getUuid());
        }

        // 根据用户类型查询账户是否存在
        AuthUserDTO authUserDTO = authUserService.getUserByUsernameAndUserType(request.getUsername(), request.getUserType());

        if (Objects.isNull(authUserDTO)) {
            throw new EModeServiceException("当前登录用户不存在本系统，请先前往注册");
        }

        // 校验账户密码  sm2解密之后再进行hash，一致则为相同
        if (ObjectUtils.notEqual(SmCryptoUtil.doHashValue(SmCryptoUtil.doSm2Decrypt(request.getPassword())), authUserDTO.getPassword())) {
            throw new EModeServiceException("密码错误");
        }

        // 执行登录逻辑
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(authUserDTO.getId());
        loginUser.setUsername(authUserDTO.getUsername());
        loginUser.setUserType(request.getUserType());
        loginUser.setMenuPermission(authUserService.getUserPermissionByIdForSet(authUserDTO.getId()));
        loginUser.setRolePermission(authUserService.getUserRoleByIdForSet(authUserDTO.getId()));
        LoginUtils.loginByDevice(loginUser, request.getUserType(), request.getDeviceBy());

        // 记录登录日志

        // 封装数据返回
        return LoginModel.builder().token(StpUtil.getTokenValue()).build();
    }

    /**
     * 校验验证码
     */
    private void checkCaptcha(String code, String uuid) {
        String redisKey = RedisConstant.CAPTCHA_CODE_KEY + uuid;
        String cacheCode = String.valueOf(
                Objects.nonNull(RedisUtils.getCacheObject(redisKey)) ? RedisUtils.getCacheObject(redisKey) : ""
        );
        if (StringUtils.isBlank(cacheCode)) {
            throw new EModeServiceException("验证码已失效");
        }
        if (ObjectUtils.notEqual(cacheCode, code)) {
            throw new EModeServiceException("验证码有误");
        }
    }
}
