package com.mini.common.utils.list;

import com.mini.common.enums.AddOrUpdate;
import com.mini.common.exception.service.EModeServiceException;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhl
 * @create 2024/5/23 10:58
 */
public class ListSpilt {

    private ListSpilt() {
    }

    /**
     * 集合按照规定大小进行切分
     */
    public static <T> List<List<T>> splitList(List<T> list, final int batchSize) {
        return splitCollection(list, batchSize);
    }

    private static <T> List<List<T>> splitCollection(List<T> list, final int batchSize) {
        return IntStream.range(0, (int) Math.ceil((double) list.size() / batchSize))
                .mapToObj(i -> list.subList(i * batchSize, Math.min(list.size(), (i + 1) * batchSize)))
                .collect(Collectors.toList());
    }

    /**
     * 集合切分处理通用泛型
     */
    @SuppressWarnings("all")
    public static  <T extends Object> Map<AddOrUpdate, List<T>> addOrUpdate(List<T> targetList, List<T> sourceList) {
        List<T> addEntityList = new ArrayList<>();
        List<T> updateEntityList = new ArrayList<>();

        List<Long> targetIdList = Collections.emptyList();
        List<Long> sourceIdlist = Collections.emptyList();

        if (CollectionUtils.isNotEmpty(targetList)) {
            targetIdList = getIdList(targetList);
        }

        if (CollectionUtils.isNotEmpty(sourceIdlist)) {
            sourceIdlist = getIdList(sourceList);
        }

        Map<AddOrUpdate, List<T>> dataMap = new HashMap<>();
        // 差集新增 target - source，
        List<Long> addList = new ArrayList<>(CollectionUtils.subtract(targetIdList, sourceIdlist));
        // 交集更新 source {} target
        List<Long> updateList = new ArrayList<>(CollectionUtils.intersection(targetIdList, sourceIdlist));

        // 需要新增
        if (CollectionUtils.isEmpty(targetList)) {
            return dataMap;
        }

        targetList.forEach(e -> {
            try {
                Field field = e.getClass().getDeclaredField("id");
                field.setAccessible(true);
                Long id = (Long) field.get(e);
                if (addList.contains(id)) {
                    addEntityList.add(e);
                }
                if (updateList.contains(id)) {
                    updateEntityList.add(e);
                }
            } catch (Exception ex) {
                throw new EModeServiceException("反射异常");
            }
        });
        dataMap.put(AddOrUpdate.ADD, addEntityList);
        dataMap.put(AddOrUpdate.UPDATE, updateEntityList);
        return dataMap;
    }

    /**
     * 反射获取集合 idList
     */
    @SuppressWarnings("all")
    private static <T extends Object> List<Long> getIdList(List<T> entityList) {
        return entityList.stream().map(e -> {
            Long id = null;
            try {
                Field field = e.getClass().getDeclaredField("id");
                field.setAccessible(true);
                id = (Long) field.get(e);
            } catch (Exception ex) {
                throw new EModeServiceException("反射异常");
            }
            return id;
        }).collect(Collectors.toList());
    }

}
