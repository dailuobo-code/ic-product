package com.mallcai.bs.enums.product;

/**
 * @Author Sjn
 * @Date 2019-07-17 17:36
 * @Description
 */
public enum ProductShowRuleEnum {

    /**
     * 门店商品售罄
     */
    HIDE_ON_SOLD_OUT("hide_on_sold_out","门店售罄不显示");

    private String code;

    private String desc;

    ProductShowRuleEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
