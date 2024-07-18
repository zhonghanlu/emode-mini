package com.mini.web;


import cn.hutool.core.net.Ipv4Util;
import com.mini.common.utils.http.IPUtils;
import com.mini.core.config.AopConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhl
 * 启动程序
 */
@Slf4j
@Import(AopConfig.class)
@EnableAsync
@EnableScheduling
@MapperScan(value = "com.mini.*.mapper")
@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages = {
                "com.mini.web.config",
                "com.mini.web.controller.*",
                "com.mini.biz.*",
                "com.mini.common.*",
                "com.mini.core.*",
                "com.mini.*.service.impl",
                "com.mini.*.mapperstruct",
                "com.mini.*.model.*"
        })
public class ModeApplication {

    public static void main(String[] args) {

        log.info("openApi3接口地址：http://{}:18099/doc.html", IPUtils.getIp());

        SpringApplication.run(ModeApplication.class, args);
    }

}
