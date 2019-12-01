package com.dailuobo.api.domain.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJSONString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseObject(Map<String, Object> map, Class<T> clazz) {
        try {
            StringBuilder sb = new StringBuilder("{");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = "\"" + entry.getKey() + "\"";
                Object value = entry.getValue();
                if (value instanceof String) {
                    if (StringUtils.isEmpty((CharSequence) value)) {
                        sb.append(key + ":null,");
                    } else {
                        sb.append(key + ":\"" + value + "\",");
                    }
                } else {
                    sb.append(key + ":" + value + ",");
                }
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("}");
            OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            return OBJECT_MAPPER.readValue(sb.toString(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
