package com.mallcai.itemcenter.utils;

import com.mallcai.itemcenter.dto.Paging;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 批量 bean mapper 包装工具
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 15:49<br/>
 */
public class GenericConverter {

    /**
     * 批量处理一个 paging
     */
    public static <S, T> Paging<T> batchConvert(Paging<S> input, Function<S, T> converter) {
        if (input == null) {
            return Paging.empty();
        }
        if (input.isEmpty()) {
            return Paging.empty();
        }
        return new Paging<>(input.getTotal(), input.getData().stream().map(converter).collect(Collectors.toList()));
    }
    /**
     * 批量处理一个 list
     */
    public static <S, T> List<T> batchConvert(List<S> input, Function<S, T> converter) {
        if (input == null) {
            return Collections.emptyList();
        }
        if (input.isEmpty()) {
            return Collections.emptyList();
        }
        return input.stream().map(converter).collect(Collectors.toList());
    }
}
