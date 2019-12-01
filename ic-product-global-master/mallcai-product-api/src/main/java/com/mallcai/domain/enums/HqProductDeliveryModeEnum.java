package com.mallcai.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lekaijun
 * @date 2019-10-17
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum HqProductDeliveryModeEnum {
    STORE_PICK(1, "门店自提"),
    DELIVERY_HOME(2, "送货上门"),
    ;

    Integer code;

    String desc;
}
