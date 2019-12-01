package com.dailuobo.api.common;

import com.dailuobo.ic.api.base.IntCodeMsgEnum;
import lombok.Getter;

/**
 * 130010000~130019999
 * see <url href="http://wiki.caicaivip.com/pages/viewpage.action?pageId=12159350"/>
 */
@Getter
public enum ResponseCodeEnum implements IntCodeMsgEnum {
    SUCCESS(200, "success"),
    FAIL(400, "fail"),
    INVALID_PARAM(401, "参数异常"),
    INVALID_OPERATION(402, "操作异常"),
    DB_OPERATION_CREATE_FAIL(601,"数据库操作，创建失败"),
    DB_OPERATION_UPATE_FAIL(602,"数据库操作，更新失败"),
    DB_OPERATION_QUERY_FAIL(603,"数据库查询失败"),
    DB_OPERATION_DEL_FAIL(604,"数据库删除失败"),

    SYSTEM_ERROR(1001,"系统异常"),


    //预留100个,从130010100开始
    PRODUCT_UNSHELVE_FORECAST_FAIL(130010100, "下架预测商品失败")
    ;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
