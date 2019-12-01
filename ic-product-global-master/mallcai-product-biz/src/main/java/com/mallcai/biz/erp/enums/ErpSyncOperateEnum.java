package com.mallcai.biz.erp.enums;

/**
 * erp
 */
public enum ErpSyncOperateEnum {
    INSERT("INSERT", "新增"),
    UPDATE( "UPDATE", "修改");

    ErpSyncOperateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ErpSyncOperateEnum getByCode(String code){

       for(ErpSyncOperateEnum operate: ErpSyncOperateEnum.values()) {

           if(operate.code.equalsIgnoreCase(code)){
               return operate;
           }
       }
       return null;
    }

}
