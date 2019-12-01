package com.dailuobo.api.domain.util;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by Cowboy on 2016/10/17.
 */
public class KeywordType {
    public static void type(String keyword, Map<String, Object> param) {
        if (!StringUtils.isEmpty(keyword)) {
            if (keyword.length() > 10) {
                param.put("orderId", keyword);
            } else {
                param.put("rfidNo", keyword);
            }
        }
    }
}
