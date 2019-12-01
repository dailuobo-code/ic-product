package com.mallcai.domain.product.request;

import com.mallcai.domain.enums.ShelveOperationEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 城市商品状态修改
 */
@Data
public class CityProductStatusChangeRequest implements Serializable {
    private static final long serialVersionUID = -206825269268881002L;

    private Integer userId;
    private Long itemId;
    private Long skuId;
    private Integer cityId;
    private Long sellerId;
    private ShelveOperationEnum status;
}
