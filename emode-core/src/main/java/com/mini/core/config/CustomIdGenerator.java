package com.mini.core.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.mini.common.utils.webmvc.IDGenerator;

/**
 * @author zhl
 */
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Long nextId(Object entity) {
        return IDGenerator.next();
    }
}