package com.mallcai.bs.enums;

/**
 * @Author: mengjia
 * @Date: 2019-05-15 16:25
 * @Version 1.0
 */
public enum DelFlagEnum {

    NORMAL(1, "正常"), UNNORMAL(0, "删除");


    private Integer value;

    private String desc;

    DelFlagEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
