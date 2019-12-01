package com.mallcai.bs.enums;


/**
 * 仓库枚举
 */
public enum WarehouseTypeEnum {

    FRESH(1, "生鲜仓"), STANDARD(2, "标品仓");

    WarehouseTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}