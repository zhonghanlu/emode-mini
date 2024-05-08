package com.mini.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 */
@EnableAsync
@EnableScheduling
//@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages = {
                "com.mini.*"
        })
public class ModeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModeApplication.class, args);
    }

}
