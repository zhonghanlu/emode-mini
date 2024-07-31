package com.mini.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mini.common.constant.HttpStatus;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.model.LoginUser;
import com.mini.common.utils.LoginUtils;
import com.mini.common.model.CommonEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author zhl
 * MP注入处理器
 */
@Slf4j
public class InjectionMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof CommonEntity) {
                CommonEntity commonEntity = (CommonEntity) metaObject.getOriginalObject();
                LocalDateTime current = Objects.nonNull(commonEntity.getCreateTime())
                        ? commonEntity.getCreateTime() : LocalDateTime.now();
                commonEntity.setCreateTime(current);
                commonEntity.setUpdateTime(current);
                LoginUser loginUser = getLoginUser();
                if (Objects.nonNull(loginUser)) {
                    Long userId = Objects.nonNull(commonEntity.getCreateBy())
                            ? commonEntity.getCreateBy() : loginUser.getUserId();
                    commonEntity.setCreateBy(userId);
                    commonEntity.setUpdateBy(userId);
                }
            }
        } catch (Exception e) {
            throw new EModeServiceException("自动注入异常 => " + e.getMessage() + HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof CommonEntity) {
                CommonEntity commonEntity = (CommonEntity) metaObject.getOriginalObject();
                LocalDateTime current = Objects.nonNull(commonEntity.getCreateTime())
                        ? commonEntity.getCreateTime() : LocalDateTime.now();
                // 更新时间填充(不管为不为空)
                commonEntity.setUpdateTime(current);
                LoginUser loginUser = getLoginUser();
                // 当前已登录 更新人填充(不管为不为空)
                if (Objects.nonNull(loginUser)) {
                    commonEntity.setUpdateBy(loginUser.getUserId());
                }
            }
        } catch (Exception e) {
            throw new EModeServiceException("自动注入异常 => " + e.getMessage() + HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取登录用户名
     */
    private LoginUser getLoginUser() {
        LoginUser loginUser;
        try {
            loginUser = LoginUtils.getLoginUser();
        } catch (Exception e) {
            return null;
        }
        return loginUser;
    }

}
