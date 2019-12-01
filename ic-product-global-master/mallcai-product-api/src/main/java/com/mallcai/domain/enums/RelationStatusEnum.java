package com.mallcai.domain.enums;

/**
 * 前台分类关联状态
 */
public enum RelationStatusEnum {
    /**
     * 启用状态
     */
    OFF(1, "启用状态"),
    ON(2, "禁用状态");

    RelationStatusEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    private Integer status;
    private String description;


    public static RelationStatusEnum getByStatus(Integer status) {
        if (status == null) {
            return null;
        }
        for (RelationStatusEnum relationStatusEnum : RelationStatusEnum.values()) {
            if (relationStatusEnum.getStatus().equals(status)) {
                return relationStatusEnum;
            }
        }
        return null;
    }


    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
