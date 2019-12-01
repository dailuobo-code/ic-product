package com.mallcai.itemcenter.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * JsonSupport
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:40<br/>
 */
public interface JsonSupport {

    ObjectMapper objectMapper = new ObjectMapper();

    TypeReference<Map<String, Object>> MAP_STRING_OBJECT = new TypeReference<Map<String, Object>>() {
    };

    TypeReference<Map<String, String>> MAP_OF_STRING = new TypeReference<Map<String, String>>() {
    };

    TypeReference<List<String>> LIST_OF_STRING = new TypeReference<List<String>>() {
    };

    TypeReference<Map<String, Long>> MAP_OF_LONG = new TypeReference<Map<String, Long>>() {
    };

    TypeReference<Map<Long, Integer>> MAP_OF_LONG_INTEGER = new TypeReference<Map<Long, Integer>>() {
    };

    /**
     * json转对象
     *
     * @param json          序列化后的json
     * @param typeReference 类型引用
     * @param supplier      默认值获取方法
     * @param error         错误语句
     * @param <T>           返回类型的泛型
     * @return 对象
     */
    default <T> T json2object(String json, TypeReference<T> typeReference, Supplier<T> supplier, String error) {
        if (StringUtils.isEmpty(json)) {
            return supplier.get();
        } else {
            try {
                return objectMapper.readValue(json, typeReference);
            } catch (IOException e) {
                throw new IllegalArgumentException(error);
            }
        }
    }

    /**
     * 对象转json
     *
     * @param object 对象
     * @param error  错误语句
     * @param <T>    对象的类型
     * @return 序列化语句
     */
    default <T> String object2json(T object, String error) {
        if (object == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(error);
        }
    }
}
