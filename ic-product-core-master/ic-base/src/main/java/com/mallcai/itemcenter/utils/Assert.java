package com.mallcai.itemcenter.utils;

import com.mallcai.itemcenter.exception.ServiceException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Assert
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/23 16:15<br/>
 */
public class Assert {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ServiceException(message);
        }
    }

    public static void nonNull(Object obj, String message) {
        if (obj == null) {
            throw new ServiceException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ServiceException(message);
        }
    }

    public static void notEmpty(Map<?,?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ServiceException(message);
        }
    }

    public static void notEmpty(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new ServiceException(message);
        }
    }
}
