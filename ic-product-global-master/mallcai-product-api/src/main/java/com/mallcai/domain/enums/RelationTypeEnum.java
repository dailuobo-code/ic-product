package com.mallcai.domain.enums;

/**
 * 前台分类关联类型
 */
public enum RelationTypeEnum {
    BACKEND_CATEGORY(1, "后台分类"),
    PRODUCT(2, "指定商品");

    RelationTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    private Integer type;
    private String description;


    public static RelationTypeEnum getByStatus(Integer type) {
        if (type == null) {
            return null;
        }
        for (RelationTypeEnum relationTypeEnum : RelationTypeEnum.values()) {
            if (relationTypeEnum.getType().equals(type)) {
                return relationTypeEnum;
            }
        }
        return null;
    }


    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
