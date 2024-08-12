package com.mini.web.runner;

import com.mini.biz.base.SysConfigBiz;
import com.mini.common.constant.RedisConstant;
import com.mini.common.utils.http.IPUtils;
import com.mini.common.utils.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhl
 * @create 2024/7/31 15:17
 */
@Slf4j
@Order(value = 2)
@Component
@RequiredArgsConstructor
public class ModeAppRunner implements ApplicationRunner {

    private final SysConfigBiz sysConfigBiz;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void run(ApplicationArguments args) {
        log.info("openApi3接口地址 http://{}:{}/doc.html", IPUtils.getIp(), serverPort);

        // 参数缓存
        Map<String, String> map = sysConfigBiz.selectAllForMap();
        if (CollectionUtils.isNotEmpty(map.keySet())) {
            map.keySet().forEach(key -> RedisUtils.setCacheObject(RedisConstant.SYS_CONFIG_KEY + key, map.get(key)));
        }
    }
}
