package com.mallcai.itemcenter.common;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * BaseConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:40<br/>
 */
public interface BaseConverter extends JsonSupport {
    default Integer getType(ConditionReadable type) {
        if (type == null) {
            return null;
        }
        return type.getValue();
    }

    default <TKey, TValue> String map2json(Map<TKey, TValue> map) {
        return object2json(map, "fail to serialize map");
    }

    default <T> String list2json(List<T> list) {
        return object2json(list, "fail to serialize list");
    }

    default List<String> json2listOfString(String json) {
        return json2object(json, LIST_OF_STRING, Collections::emptyList, "fail to deserialize json to list of string");
    }

    default <TKey, TValue> Map<TKey, TValue> map2map(Map<TKey, TValue> source) {
        return source;
    }

    default <T> List<T> list2list(List<T> source) {
        return source;
    }

    default Map<String, String> json2mapOfString(String json) {
        return json2object(json, MAP_OF_STRING, Collections::emptyMap, "fail to deserialize json");
    }

    default Map<String, Object> json2mapOfObject(String json) {
        return json2object(json, MAP_STRING_OBJECT, Collections::emptyMap, "fail to deserialize json");
    }
}
