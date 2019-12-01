package com.mallcai.domain.enums;

/**
 * 同步类型枚举
 */
public enum AsyncLogTypeEnum {

    HQ_PRODUCT_ASYNC(1, "总部商品同步"),
    CITY_PRODUCT_ASYNC(2, "城市商品同步"),
    SPEC_ASYNC(3, "规格同步"),
    PRODUCT_GROUP_ASYNC(4, "商品组创建同步"),
    PRODUCT_SHELVE_ON(5, "商品上架"),
    PRODUCT_SHELVE_OFF(6, "商品下架");

    AsyncLogTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public java.lang.String getMsg() {
        return msg;
    }
}
