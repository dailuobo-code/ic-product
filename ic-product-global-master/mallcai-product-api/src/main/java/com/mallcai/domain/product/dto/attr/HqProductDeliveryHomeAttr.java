package com.mallcai.domain.product.dto.attr;

import com.mallcai.domain.product.dto.HqProductExtraAttrDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 送货上门属性
 * @author wangshifeng
 * @date 2019-09-03 17:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HqProductDeliveryHomeAttr extends HqProductExtraAttrDTO {

    /**
     * 送货上门时间
     */
    private Integer arrivalDay;

    /**
     * 商户名称
     */
    private String merchantName;

}
