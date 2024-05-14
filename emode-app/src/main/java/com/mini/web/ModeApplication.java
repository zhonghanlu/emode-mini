package com.mini.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhl
 * 启动程序
 */
@EnableAsync
@EnableScheduling
@MapperScan(value = "com.mini.*.mapper")
@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages = {
                "com.mini.common.utils",
                "com.mini.biz.*",
                "com.mini.*.service.impl"
        })
public class ModeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModeApplication.class, args);
    }

}
