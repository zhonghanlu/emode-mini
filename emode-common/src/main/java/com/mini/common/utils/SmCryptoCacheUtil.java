package com.mini.common.utils;

import com.mini.common.utils.redis.RedisUtils;

import static com.mini.common.constant.RedisConstant.SM2_CACHE_KEY_PREFIX;

/**
 * @author zhl
 * @create 2024/8/5 10:25
 */
public class SmCryptoCacheUtil {

    private SmCryptoCacheUtil() {
    }

    public static String doSm2Decrypt(String str) {
        String redisKey = SM2_CACHE_KEY_PREFIX + str;
        // 先从缓存中获取解密后的明文
        String decryptedText = RedisUtils.getCacheObject(redisKey);
        if (decryptedText != null) {
            return decryptedText;
        }

        // 如果缓存中没有，则执行解密操作
        decryptedText = SmCryptoUtil.doSm2Decrypt(str);

        // 将解密后的结果存入缓存
        RedisUtils.setCacheObject(redisKey, decryptedText);

        return decryptedText;
    }

}
