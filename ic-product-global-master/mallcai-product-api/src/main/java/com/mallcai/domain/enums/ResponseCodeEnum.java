package com.mallcai.domain.enums;

public enum ResponseCodeEnum {
    SUCCESS(200, "success"),
    FAIL(400, "fail"),
    INVALID_PARAM(401, "参数异常"),
    INVALID_OPERATION(402, "操作异常"),
    DB_OPERATION_CREATE_FAIL(601,"数据库操作，创建失败"),
    DB_OPERATION_UPATE_FAIL(602,"数据库操作，更新失败"),
    DB_OPERATION_QUERY_FAIL(603,"数据库查询失败"),
    DB_OPERATION_DEL_FAIL(604,"数据库删除失败"),

    SYSTEM_ERROR(1001,"系统异常");

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    /**
     * 业务状态码
     */
    private Integer code;

    /**
     * 业务消息
     */
    private String msg;
}
