package com.dailuobo.ic.api.enums;

public enum CategoryRelationTypeEnum {
    BACKEND_CATEGORY(1, "后台类目"),
    CITY_PRODUCT(2, "城市商品");

    CategoryRelationTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer code;
    private String description;


    public static CategoryRelationTypeEnum getByStatus(Integer code) {
        if (code == null) {
            return null;
        }
        for (CategoryRelationTypeEnum relationTypeEnum : CategoryRelationTypeEnum.values()) {
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
