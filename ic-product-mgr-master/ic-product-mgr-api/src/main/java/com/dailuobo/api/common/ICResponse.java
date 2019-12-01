package com.dailuobo.api.common;

import java.io.Serializable;

public class ICResponse<T> implements Serializable {

    private static final long serialVersionUID = 7738824599489444909L;
    /**
     * 返回的业务code，一般成功就是200，其他看定义
     */
    private Integer code;

    private T data;

    private PageDTO page;

    private String msg;

    private ICResponse() {

    }

    private ICResponse(T data, Integer code, String msg, PageDTO page) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.page = page;
    }

    private ICResponse(Integer code, String msg) {
        this(null, code, msg, null);
    }

    public static <T> ICResponse<T> success(T data) {
        ICResponse<T> response = new ICResponse<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> ICResponse<T> success() {
        return new ICResponse<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
    }

    public static <T> ICResponse<T> success(T data, PageDTO page) {
        ICResponse<T> response = new ICResponse<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
        response.setData(data);
        response.setPage(page);
        return response;
    }

    public static <T> ICResponse<T> fail(Integer code, String msg) {
        return new ICResponse<>(code, msg);
    }

    public static <T> ICResponse<T> fail(String msg) {
        return new ICResponse<>(ResponseCodeEnum.FAIL.getCode(), msg);
    }

    public static <T> ICResponse<T> fail(ResponseCodeEnum responseCodeEnum) {
        return new ICResponse<>(responseCodeEnum.getCode(), responseCodeEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageDTO getPage() {
        return page;
    }

    public void setPage(PageDTO page) {
        this.page = page;
    }

    public boolean isSuccess() {
        return code != null && (code.intValue() == ResponseCodeEnum.SUCCESS.getCode());
    }
}
