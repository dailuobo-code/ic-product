package com.mallcai.domain.enums;

/**
 * 前台类目生效范围
 */
public enum EffectScopeEnum {

    ALL_STORE(1, "所有门店"),
    SPECIFIC_STORES(2, "指定门店"),
    STORE_GROUP(3, "指定门店组");

    EffectScopeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer code;
    private String description;


    public static EffectScopeEnum getByStatus(Integer code) {
        if (code == null) {
            return null;
        }
        for (EffectScopeEnum relationTypeEnum : EffectScopeEnum.values()) {
            if (relationTypeEnum.getCode().equals(code)) {
                return relationTypeEnum;
            }
        }
        return null;
    }


    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
