package com.mini.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mini.common.utils.webmvc.CommonEntity;
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
//                LoginUser loginUser = getLoginUser();
//                if (Objects.nonNull(loginUser)) {
//                    Long userId = Objects.nonNull(commonEntity.getCreateBy())
//                            ? commonEntity.getCreateBy() : loginUser.getUserId();
                    // 当前已登录 且 创建人为空 则填充
//                    commonEntity.setCreateBy(userId);
                    // 当前已登录 且 更新人为空 则填充
//                    commonEntity.setUpdateBy(userId);
//                }
            }
        } catch (Exception e) {
//            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
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
//                LoginUser loginUser = getLoginUser();
//                // 当前已登录 更新人填充(不管为不为空)
//                if (Objects.nonNull(loginUser)) {
//                    commonEntity.setUpdateBy(loginUser.getUserId());
//                }
            }
        } catch (Exception e) {
//            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }

    /**
     * 获取登录用户名
     */
//    private LoginUser getLoginUser() {
//        LoginUser loginUser;
//        try {
//            loginUser = SecurityUtils.getLoginUser();
//        } catch (Exception e) {
//            log.warn("自动注入警告 => 用户未登录");
//            return null;
//        }
//        return loginUser;
//    }

}
