package com.mallcai.domain.enums;

import com.mallcai.domain.product.dto.attr.HqProductDeliveryHomeAttr;
import com.mallcai.domain.product.dto.attr.HqProductThirdPartyAttr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 商品扩展属性
 * @author lekaijun
 * @date 2019-10-17
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum HqProductExtraAttrTypeEnum {


    THIRD_PARTY(HqProductThirdPartyAttr.class.getName(), "第三方商家"),
    DELIVERY_HOME(HqProductDeliveryHomeAttr.class.getName(), "送货上门"),
    ;

    String code;

    String desc;


    public static HqProductExtraAttrTypeEnum fromName(String name) {
        return fromName(name, null);
    }

    public static HqProductExtraAttrTypeEnum fromName(String name, HqProductExtraAttrTypeEnum defaultValue) {
        if (StringUtils.isEmpty(name)) {
            return defaultValue;
        }
        for (HqProductExtraAttrTypeEnum t : HqProductExtraAttrTypeEnum.values()) {
            if (name.equalsIgnoreCase(t.name())) {
                return t;
            }
        }
        return defaultValue;
    }

}
