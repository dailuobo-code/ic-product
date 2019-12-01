package com.dailuobo.ic.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImportBusinessEnum {
    simple(1, "普通商品导入"),
    fullReduction(2, "多级满减商品导入"),
    news(3, "新人专享"),
    storeChangePrice(4, "门店调价"),
    CHANGGPRICEFROMCITY(5, "批量调价-指定城市"),
    CHANGGPRICEFROMSTORE(6, "批量调价-指定门店"),
    CHANGGPRICEFROMVIP(7, "批量调价-会员折扣"),
    INVENTORYIMPORT(8, "库存导入"),
    CHANGE_PRICE_FROM_STORE_GROUP(9,"批量调价-指定门店分组"),
    CHANGE_PRICE_FROM_BATCH_STORE_GROUP(11,"批量调价-批量门店分组"),
    HQPRODUCT_PRODUCTNO(21,"总部商品导入");
    private Integer code;
    private String desc;
}
