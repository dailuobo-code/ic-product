package com.mallcai.itemcenter.sku.enums;

import lombok.Getter;

/**
 * SalesType
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 19:18<br/>
 */
@Getter
public enum  SalesType {
    WEIGHT(1, "按重量销售"),
    QUANTITY(2, "按数量销售");

    private final int value;
    private final String description;

    SalesType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static SalesType fromValue(Integer value) {
        return fromValue(value, null);
    }

    public static SalesType fromValue(Integer value, SalesType defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        for (SalesType t : SalesType.values()) {
            if (value.equals(t.value)) {
                return t;
            }
        }
        return defaultValue;
    }
}
