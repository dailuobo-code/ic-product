package com.dailuobo.api.enums;

public enum ForecastFlagEnum {


    /**
     *
     */
    ENABLE(1, "启用预测"),
    DISABLE(0, "不启用预测");

    ForecastFlagEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static ForecastFlagEnum getForecastFlagByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ForecastFlagEnum forecastFlagEnum : ForecastFlagEnum.values()) {
            if (code.equals(forecastFlagEnum.getCode())) {
                return forecastFlagEnum;
            }
        }
        return null;

    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String msg;
}
