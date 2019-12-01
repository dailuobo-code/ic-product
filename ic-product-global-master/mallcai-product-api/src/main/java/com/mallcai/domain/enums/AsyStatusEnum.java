package com.mallcai.domain.enums;

public enum AsyStatusEnum {
    PART_SUCCESS(1, "部分成功"),
    SUCCESS(2, "全部成功"),
    Fail(3, "全部失败");


    AsyStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }


    /**
     * 编码
     */
    private Integer code;
    private String value;

    public Integer getCode() {
        return code;
    }


    public String getValue() {
        return value;
    }

}
