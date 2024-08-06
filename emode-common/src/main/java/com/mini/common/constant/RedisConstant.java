package com.mini.common.constant;

/**
 * @author zhl
 * @create 2024/7/17 11:01
 */
public final class RedisConstant {

    private RedisConstant() {
    }

    /**
     * 验证码相关
     */
    public static final String CAPTCHA_CODE_KEY = "captcha:code:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    ///////////////////////////////////////参数redisKey//////////////////////////////////////////////
    /**
     * 系统参数redis key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    ///////////////////////////////////////加密redisKey//////////////////////////////////////////////
    /**
     * sm解密参数redis key
     */
    public static final String SM2_CACHE_KEY_PREFIX = "sm2-decrypt-cache:";

}
