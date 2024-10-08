package com.mini.core.config;

import com.mini.core.config.properties.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author zhl
 * redis配置
 * @source_auth ruoyi-plus
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private static final String REDIS_PROTOCOL_PREFIX = "redis://";
    private static final String REDISS_PROTOCOL_PREFIX = "rediss://";

    @Resource
    private RedisProperties redisProperties;

    @Resource
    private RedissonProperties redissonProperties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redisson() {
        String prefix = REDIS_PROTOCOL_PREFIX;
        if (redisProperties.isSsl()) {
            prefix = REDISS_PROTOCOL_PREFIX;
        }
        Config config = new Config();
        config.setThreads(redissonProperties.getThreads())
                .setNettyThreads(redissonProperties.getNettyThreads())
                .setCodec(JsonJacksonCodec.INSTANCE)
                .setTransportMode(redissonProperties.getTransportMode());

        RedissonProperties.SingleServerConfig singleServerConfig = redissonProperties.getSingleServerConfig();
        if (Objects.nonNull(singleServerConfig)) {
            // 使用单机模式
            config.useSingleServer()
                    .setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
                    .setConnectTimeout(((Long) redisProperties.getTimeout().toMillis()).intValue())
                    .setDatabase(redisProperties.getDatabase())
                    .setPassword(StringUtils.isNotBlank(redisProperties.getPassword()) ? redisProperties.getPassword() : null)
                    .setTimeout(singleServerConfig.getTimeout())
                    .setRetryAttempts(singleServerConfig.getRetryAttempts())
                    .setRetryInterval(singleServerConfig.getRetryInterval())
                    .setSubscriptionsPerConnection(singleServerConfig.getSubscriptionsPerConnection())
                    .setClientName(singleServerConfig.getClientName())
                    .setIdleConnectionTimeout(singleServerConfig.getIdleConnectionTimeout())
                    .setSubscriptionConnectionMinimumIdleSize(singleServerConfig.getSubscriptionConnectionMinimumIdleSize())
                    .setSubscriptionConnectionPoolSize(singleServerConfig.getSubscriptionConnectionPoolSize())
                    .setConnectionMinimumIdleSize(singleServerConfig.getConnectionMinimumIdleSize())
                    .setConnectionPoolSize(singleServerConfig.getConnectionPoolSize())
                    .setDnsMonitoringInterval(singleServerConfig.getDnsMonitoringInterval());
        }
        // 集群配置方式 参考下方注释
        RedissonProperties.ClusterServersConfig clusterServersConfig = redissonProperties.getClusterServersConfig();
        if (Objects.nonNull(clusterServersConfig)) {
            // 使用集群模式
            config.useClusterServers()
                    .setConnectTimeout(((Long) redisProperties.getTimeout().toMillis()).intValue())
                    .setPassword(StringUtils.isNotBlank(redisProperties.getPassword()) ? redisProperties.getPassword() : null)
                    .setTimeout(clusterServersConfig.getTimeout())
                    .setRetryAttempts(clusterServersConfig.getRetryAttempts())
                    .setRetryInterval(clusterServersConfig.getRetryInterval())
                    .setSubscriptionsPerConnection(clusterServersConfig.getSubscriptionsPerConnection())
                    .setClientName(clusterServersConfig.getClientName())
                    .setIdleConnectionTimeout(clusterServersConfig.getIdleConnectionTimeout())
                    .setPingConnectionInterval(clusterServersConfig.getPingConnectionInterval())
                    .setSubscriptionConnectionMinimumIdleSize(clusterServersConfig.getSubscriptionConnectionMinimumIdleSize())
                    .setSubscriptionConnectionPoolSize(clusterServersConfig.getSubscriptionConnectionPoolSize())
                    .setMasterConnectionMinimumIdleSize(clusterServersConfig.getMasterConnectionMinimumIdleSize())
                    .setMasterConnectionPoolSize(clusterServersConfig.getMasterConnectionPoolSize())
                    .setSlaveConnectionMinimumIdleSize(clusterServersConfig.getSlaveConnectionMinimumIdleSize())
                    .setSlaveConnectionPoolSize(clusterServersConfig.getSlaveConnectionPoolSize())
                    .setDnsMonitoringInterval(clusterServersConfig.getDnsMonitoringInterval())
                    .setFailedSlaveReconnectionInterval(clusterServersConfig.getFailedSlaveReconnectionInterval())
                    .setScanInterval(clusterServersConfig.getScanInterval())
                    .setReadMode(clusterServersConfig.getReadMode())
                    .setSubscriptionMode(clusterServersConfig.getSubscriptionMode())
                    .setNodeAddresses(redisProperties.getCluster().getNodes());
        }
        RedissonClient redissonClient = Redisson.create(config);
        log.info("初始化 redis 配置");
        return redissonClient;
    }

    /**
     * 整合spring-cache
     * # redisson 缓存配置
     * redisson:
     *   cacheGroup:
     *     # 用例: @Cacheable(cacheNames="groupId", key="#XXX") 方可使用缓存组配置
     *     - groupId: redissonCacheMap
     *       # 组过期时间(脚本监控)
     *       ttl: 60000
     *       # 组最大空闲时间(脚本监控)
     *       maxIdleTime: 60000
     *       # 组最大长度
     *       maxSize: 0
     *     - groupId: testCache
     *       ttl: 1000
     *       maxIdleTime: 500
     */
//    @Bean
//    public CacheManager cacheManager(RedissonClient redissonClient) {
//        List<RedissonProperties.CacheGroup> cacheGroup = redissonProperties.getCacheGroup();
//        Map<String, CacheConfig> config = new HashMap<>();
//        for (RedissonProperties.CacheGroup group : cacheGroup) {
//            CacheConfig cacheConfig = new CacheConfig(group.getTtl(), group.getMaxIdleTime());
//            cacheConfig.setMaxSize(group.getMaxSize());
//            config.put(group.getGroupId(), cacheConfig);
//        }
//        return new RedissonSpringCacheManager(redissonClient, config, JsonJacksonCodec.INSTANCE);
//    }

    /**
     * redis集群配置 yml
     *
     * --- # redis 集群配置(单机与集群只能开启一个另一个需要注释掉)
     * spring:
     *   redis:
     *     cluster:
     *       nodes:
     *         - 192.168.0.100:6379
     *         - 192.168.0.101:6379
     *         - 192.168.0.102:6379
     *     # 密码
     *     password:
     *     # 连接超时时间
     *     timeout: 10s
     *     # 是否开启ssl
     *     ssl: false
     *
     * redisson:
     *   # 线程池数量
     *   threads: 16
     *   # Netty线程池数量
     *   nettyThreads: 32
     *   # 传输模式
     *   transportMode: "NIO"
     *   # 集群配置
     *   clusterServersConfig:
     *     # 客户端名称
     *     clientName: ${ruoyi.name}
     *     # master最小空闲连接数
     *     masterConnectionMinimumIdleSize: 32
     *     # master连接池大小
     *     masterConnectionPoolSize: 64
     *     # slave最小空闲连接数
     *     slaveConnectionMinimumIdleSize: 32
     *     # slave连接池大小
     *     slaveConnectionPoolSize: 64
     *     # 连接空闲超时，单位：毫秒
     *     idleConnectionTimeout: 10000
     *     # ping连接间隔
     *     pingConnectionInterval: 1000
     *     # 命令等待超时，单位：毫秒
     *     timeout: 3000
     *     # 如果尝试在此限制之内发送成功，则开始启用 timeout 计时。
     *     retryAttempts: 3
     *     # 命令重试发送时间间隔，单位：毫秒
     *     retryInterval: 1500
     *     # 从可用服务器的内部列表中排除 Redis Slave 重新连接尝试的间隔。
     *     failedSlaveReconnectionInterval: 3000
     *     # 发布和订阅连接池最小空闲连接数
     *     subscriptionConnectionMinimumIdleSize: 1
     *     # 发布和订阅连接池大小
     *     subscriptionConnectionPoolSize: 50
     *     # 单个连接最大订阅数量
     *     subscriptionsPerConnection: 5
     *     # 扫描间隔
     *     scanInterval: 1000
     *     # DNS监测时间间隔，单位：毫秒
     *     dnsMonitoringInterval: 5000
     *     # 读取模式
     *     readMode: "SLAVE"
     *     # 订阅模式
     *     subscriptionMode: "MASTER"
     */

}
