package com.mini.common.utils.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.common.constant.LastSql;
import com.mini.common.enums.number.Delete;
import com.mini.common.model.CommonEntity;

import java.util.Objects;

/**
 * @author zhl
 * @create 2024/5/14 15:57
 */
public class CommonMybatisUtil {
    private CommonMybatisUtil() {
    }

    /**
     * 根据id判断库内数据是否存在
     */
    public static <T extends CommonEntity> boolean isExistById(long id, BaseMapper<T> baseMapper) {
        T byId = getById(id, baseMapper);
        if (Objects.isNull(byId)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 根据id获得库内数据
     */
    public static <T extends CommonEntity> T getById(long id, BaseMapper<T> baseMapper) {
        QueryWrapper<T> queryWrapper = Wrappers.query();

        queryWrapper.eq("id", id)
                .eq("del_flag", Delete.NO)
                .last(LastSql.LIMIT_ONE);

        return baseMapper.selectOne(queryWrapper);
    }


}
