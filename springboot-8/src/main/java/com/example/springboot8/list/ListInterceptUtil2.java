package com.example.springboot8.list;

import cn.hutool.core.collection.CollectionUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListInterceptUtil2 extends CollectionUtil {

    /**
     * 次数
     * @param cap  容量
     * @param size 分割大小
     * @return
     */
    private static Integer counStep(Integer cap, Integer size) {
        if (size == null || size < 1) {
            size = 100;
        }
        return (cap + size - 1) / size;
    }

    /**
     * 分割List
     * @param list 集合
     * @param size 大小
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> intercept(List<T> list, int size) {
        int limit = counStep(list.size(), size);
        return Stream.iterate(0, n -> n + 1).limit(limit).parallel()
                .map(a -> list.stream().skip((long) a * size).limit(size).parallel().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
