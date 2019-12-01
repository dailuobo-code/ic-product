package com.dailuobo.ic.api.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * AuditStatus
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/27 21:06<br/>
 */
public enum AuditStatus {
    NORMAL(0, "正常，未审批"),
    AUDITING(10, "审批中"),
    APPROVAL(20, "审批通过"),
    REJECT(-1, "审批驳回");

    private final int value;
    private final String desc;

    AuditStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AuditStatus fromValue(Integer value) {
        return fromValue(value, null);
    }

    public static AuditStatus fromValue(Integer value, AuditStatus defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        for (AuditStatus t : AuditStatus.values()) {
            if (value.equals(t.value)) {
                return t;
            }
        }
        return defaultValue;
    }

    public static AuditStatus fromName(String name) {
        return fromName(name, null);
    }

    public static AuditStatus fromName(String name, AuditStatus defaultValue) {
        if (StringUtils.isEmpty(name)) {
            return defaultValue;
        }
        for (AuditStatus t : AuditStatus.values()) {
            if (name.equalsIgnoreCase(t.name())) {
                return t;
            }
        }
        return defaultValue;
    }
}
