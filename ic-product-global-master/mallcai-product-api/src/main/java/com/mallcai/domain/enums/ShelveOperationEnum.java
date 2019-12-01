package com.mallcai.domain.enums;

public enum ShelveOperationEnum {
    ON(1, "上架"),
    OFF(2, "下架");

    ShelveOperationEnum(Integer value, String desc) {
        this.value = value;
        this.name = desc;
    }

    private Integer value;
    private String name;


    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
