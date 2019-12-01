package com.mallcai.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 送货上门属性
 * @author wangshifeng
 * @date 2019-09-03 17:06
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeliveryHomeAttrDTO implements Serializable {

    private static final long serialVersionUID = 5247865597605532274L;
    /**
     * 送货上门时间
     */
    private Integer arrivalDay;

}
