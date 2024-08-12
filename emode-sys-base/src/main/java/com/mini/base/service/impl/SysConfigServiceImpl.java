package com.mini.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.base.entity.SysConfig;
import com.mini.base.mapper.SysConfigMapper;
import com.mini.base.mapperstruct.SysConfigStructMapper;
import com.mini.base.model.dto.SysConfigDTO;
import com.mini.base.model.query.SysConfigQuery;
import com.mini.base.service.ISysConfigService;
import com.mini.common.constant.LastSql;
import com.mini.common.constant.RedisConstant;
import com.mini.common.enums.number.Delete;
import com.mini.common.enums.str.YesOrNo;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.mybatis.CommonMybatisUtil;
import com.mini.common.utils.redis.RedisUtils;
import com.mini.common.utils.webmvc.IDGenerator;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhl
 * @create 2024/7/31 14:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysConfigServiceImpl implements ISysConfigService {

    private final SysConfigMapper sysConfigMapper;

    @Override
    public void insert(SysConfigDTO dto) {
        String configKey = dto.getConfigKey();

        LambdaQueryWrapper<SysConfig> queryWrapper = Wrappers.lambdaQuery(SysConfig.class);
        queryWrapper.eq(SysConfig::getConfigKey, configKey)
                .eq(SysConfig::getDelFlag, Delete.NO)
                .last(LastSql.LIMIT_ONE);
        SysConfig sysConfig = sysConfigMapper.selectOne(queryWrapper);

        if (Objects.nonNull(sysConfig)) {
            throw new EModeServiceException("当前key重复，key:" + configKey);
        }

        dto.setId(IDGenerator.next());
        dto.setShowStatus(YesOrNo.YES);

        SysConfig sysConfig1 = SysConfigStructMapper.INSTANCE.dto2Entity(dto);

        int b = sysConfigMapper.insert(sysConfig1);

        if (b <= 0) {
            throw new EModeServiceException("新增失败");
        }

        // 键入缓存
        RedisUtils.setCacheObject(getRedisKey(configKey), sysConfig1.getConfigValue());
    }

    @Override
    public void del(long id) {
        if (id <= 0) {
            throw new EModeServiceException("id有误，id：" + id);
        }

        SysConfig sysConfig = CommonMybatisUtil.getById(id, sysConfigMapper);

        if (Objects.isNull(sysConfig)) {
            throw new EModeServiceException("当前id不存在");
        }

        sysConfig.setDelFlag(Delete.YES);

        int b = sysConfigMapper.updateById(sysConfig);

        if (b <= 0) {
            throw new EModeServiceException("删除失败，id:" + id);
        }

        // 删除缓存
        RedisUtils.deleteKeys(getRedisKey(sysConfig.getConfigKey()));
    }

    @Override
    public void update(SysConfigDTO dto) {
        long id = dto.getId();
        SysConfig sysConfig = CommonMybatisUtil.getById(id, sysConfigMapper);

        if (Objects.isNull(sysConfig)) {
            throw new EModeServiceException("当前id不存在");
        }

        SysConfig sysConfig1 = SysConfigStructMapper.INSTANCE.dto2Entity(dto);

        int b = sysConfigMapper.updateById(sysConfig1);

        if (b <= 0) {
            throw new EModeServiceException("更新失败，id:" + id);
        }

        // 键入缓存
        RedisUtils.setCacheObject(getRedisKey(sysConfig1.getConfigKey()), sysConfig1.getConfigValue());
    }

    @Override
    public String searchByKey(String key) {
        String configValue = RedisUtils.getCacheObject(getRedisKey(key));

        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }

        LambdaQueryWrapper<SysConfig> wrapper = Wrappers.lambdaQuery(SysConfig.class);
        wrapper.eq(SysConfig::getConfigKey, key)
                .eq(SysConfig::getDelFlag, Delete.NO)
                .last(LastSql.LIMIT_ONE);
        return sysConfigMapper.selectOne(wrapper).getConfigValue();
    }

    @Override
    public IPage<SysConfigDTO> selectPage(SysConfigQuery query) {
        return sysConfigMapper.selectPage(query, query.build());
    }

    @Override
    public Map<String, String> selectAllForMap() {
        LambdaQueryWrapper<SysConfig> wrapper = Wrappers.lambdaQuery(SysConfig.class);
        wrapper.eq(SysConfig::getDelFlag, Delete.NO)
                .eq(SysConfig::getConfigStatus, YesOrNo.YES);

        List<SysConfig> sysConfigList = sysConfigMapper.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(sysConfigList)) {
            // key 必定唯一
            return sysConfigList.stream()
                    .collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue, (k, v) -> k));
        }
        return Collections.emptyMap();
    }

    /**
     * 获取redis key
     */
    public String getRedisKey(String key) {
        return RedisConstant.SYS_CONFIG_KEY + key;
    }
}
