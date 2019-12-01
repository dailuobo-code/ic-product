package com.mallcai.domain.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * ChargeMethod
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 16:42<br/>
 */
public enum ChargeMethod {
    FIXED(1, "固定计费"),
    AMOUNT(2, "按量计费");

    @Getter
    private final int value;
    @Getter
    private final String detail;

    ChargeMethod(int value, String detail) {
        this.value = value;
        this.detail = detail;
    }

    public static ChargeMethod fromValue(String name) {
        return fromValue(name, null);
    }

    public static ChargeMethod fromValue(String name, ChargeMethod defaultValue) {
        if (StringUtils.isEmpty(name)) {
            return defaultValue;
        }
        for (ChargeMethod t : ChargeMethod.values()) {
            if (name.equalsIgnoreCase(t.name())) {
                return t;
            }
        }
        return defaultValue;
    }

    public static ChargeMethod fromValue(Integer value) {
        return fromValue(value, null);
    }

    public static ChargeMethod fromValue(Integer value, ChargeMethod defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        for (ChargeMethod t : ChargeMethod.values()) {
            if (value.equals(t.value)) {
                return t;
            }
        }
        return defaultValue;
    }
}
