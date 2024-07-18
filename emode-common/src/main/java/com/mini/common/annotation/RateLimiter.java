package com.mini.common.annotation;

import com.mini.common.constant.RedisConstant;
import com.mini.common.enums.LimitType;

import java.lang.annotation.*;

/**
 * 限流注解
 * @author zhl
 * @source_author ruoyi-plus
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    String key() default RedisConstant.RATE_LIMIT_KEY;

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 100;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
