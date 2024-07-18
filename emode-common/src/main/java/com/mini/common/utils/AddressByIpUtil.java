package com.mini.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhl
 * @create 2024/7/18 16:19
 */
@Slf4j
public class AddressByIpUtil {

    // ip2region.xdb 文件地址常量（本地xdb文件路径）
    private static String XDB_PATH = "emode-app/src/main/resources/ip/ip2region.xdb";


    private static final ThreadLocal<Searcher> SEARCHER_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        try {
            return Searcher.newWithFileOnly(XDB_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Searcher instance", e);
        }
    });

    /**
     * 完全基于ip2region.xdb文件，对用户ip地址进行转换
     * 注：并发调用时，每个线程需创建一个独立的searcher对象 单独使用。
     */
    public static String getIpPossessionByFile(String ip) {
        if (StringUtils.isNotEmpty(ip)) {
            try {
                // 1、创建 searcher 对象
                Searcher searcher = SEARCHER_THREAD_LOCAL.get();
                // 2、查询
                long sTime = System.nanoTime();
                String region = searcher.search(ip);
                long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
                region = region.replace("|0", "");
                log.info("{地区: {}, IO操作数: {}, 耗时: {} μs}", region, searcher.getIOCount(), cost);
                return region;
            } catch (Exception e) {
                log.error("获取IP地址异常：{} ", e.getMessage());
                throw new RuntimeException("获取IP地址异常");
            }
        }
        return "未知";
    }

}
