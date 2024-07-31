package com.mini.web;


import com.mini.core.config.AopConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
                "com.mini.web.runner",
                "com.mini.biz.*",
                "com.mini.common.*",
                "com.mini.core.*",
                "com.mini.*.service.impl",
                "com.mini.*.mapperstruct",
                "com.mini.*.model.*"
        })
public class ModeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModeApplication.class, args);
    }

}
