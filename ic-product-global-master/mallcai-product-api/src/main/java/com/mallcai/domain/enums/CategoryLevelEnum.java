package com.mallcai.domain.enums;

public enum CategoryLevelEnum {
    LEVEL_ONE(1, "一级分类"),
    LEVEL_TWO(2, "二级分类");

    CategoryLevelEnum(Integer code, String desc) {
        this.code = code;
        this.description = desc;
    }

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
