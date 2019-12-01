package com.dailuobo.api.enums;

/**
 * @ClassName: CityStatusEnum
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/9/18 下午2:25
 * @Version: 1.0
 **/
public enum CityStatusEnum {
    ON_SHELF(1, "上架"),
    OFF_SHELF(2, "下架");

    private int status;
    private String desc;

    private CityStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return this.status;
    }

}
