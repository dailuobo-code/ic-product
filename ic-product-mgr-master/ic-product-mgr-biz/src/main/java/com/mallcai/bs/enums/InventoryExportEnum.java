package com.mallcai.bs.enums;

/**
 * @ClassName: InventoryExportEnum
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/6/26 上午10:09
 * @Version: 1.0
 **/
public enum InventoryExportEnum {
    RUNNING((byte)0, "导出中"),
    SUCCESS((byte)1, "导出成功"),
    FAILED((byte)2, "导出失败"),
    NO_DATA((byte)3, "无数据可以导出");


    private byte status;
    private String desc;

    InventoryExportEnum(byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public byte getStatus() {
        return status;
    }
}
