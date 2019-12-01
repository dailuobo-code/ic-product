package com.dailuobo.ic.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModifyTypeEnum {
    HqProduct(1, "总部商品"),
    CityProduct(2, "城市商品");
    private Integer code;
    private String desc;
}
