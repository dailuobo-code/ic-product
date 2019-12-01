package com.mallcai.biz.erp.enums;

/**
 * erp
 */
public enum ErpSyncTableEnum {
    WAREHOUSE("erp_warehouse", "仓库表"),
    PURCHASE_UPDATE( "erp_purchase_order", "采购表");

    ErpSyncTableEnum(String code, String desc) {
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

    public static ErpSyncTableEnum getByCode(String code){

       for(ErpSyncTableEnum operate: ErpSyncTableEnum.values()) {

           if(operate.code.equals(code)){
               return operate;
           }
       }
       return null;
    }

}
