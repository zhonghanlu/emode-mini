package com.mini.core.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mini.common.enums.number.Delete;
import com.mini.common.enums.str.*;
import com.mini.common.enums.converter.IntEnumTypeHandler;
import com.mini.common.enums.converter.StringEnumTypeHandler;
import com.mini.common.mybatis.handler.InjectionMetaObjectHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhl
 * Mybatis Plus 配置
 */
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisPlusConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
            // 注册自定义的IntEnumTypeHandler
            registry.register(Delete.class, IntEnumTypeHandler.class);
            // 注册自定义的StringEnumTypeHandler
            registry.register(Gender.class, StringEnumTypeHandler.class);
            registry.register(YesOrNo.class, StringEnumTypeHandler.class);
            registry.register(Device.class, StringEnumTypeHandler.class);
            registry.register(FileType.class, StringEnumTypeHandler.class);
            registry.register(MenuType.class, StringEnumTypeHandler.class);
            registry.register(UserType.class, StringEnumTypeHandler.class);
            registry.register(LoginOptType.class, StringEnumTypeHandler.class);
            registry.register(MethodType.class, StringEnumTypeHandler.class);
            registry.register(NoticeType.class, StringEnumTypeHandler.class);
            registry.register(MessageStatus.class, StringEnumTypeHandler.class);
        };
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor());
        // 乐观锁插件
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        // 阻断插件
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor());
        return interceptor;
    }

    /**
     * 分页插件，自动识别数据库类型
     */
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置数据库类型为mysql
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInnerInterceptor.setMaxLimit(-1L);
        return paginationInnerInterceptor;
    }

    /**
     * 乐观锁插件
     */
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 如果是对全表的删除或更新操作，就会终止该操作
     */
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }


    /**
     * 使用网卡信息绑定雪花生成器
     * 防止集群雪花ID重复
     */
    @Bean
    public IdentifierGenerator idGenerator() {
        return new CustomIdGenerator();
    }

    /**
     * 元对象字段填充控制器
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new InjectionMetaObjectHandler();
    }
}