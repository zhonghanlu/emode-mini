package com.mini.common.utils.list;

import java.util.List;
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

}
