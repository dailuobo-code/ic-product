package com.dailuobo.ic.api.base;

import lombok.*;

/**
 * 提供单商品的精确查找
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SingleCityProductQueryRequest extends CityBaseRequest {
    /**
     * 城市商品id
     */
    private Integer cityProductId;
    /**
     * 商品No;
     */
    private String productNo;


    @Override
    public boolean checkParams() {
        if (cityProductId == null && productNo == null) {
            throw new IllegalArgumentException("cityProductId,ProdNo,one  least");
        }
        if (cityId == null) {
            throw new IllegalArgumentException("cityId can not be null");
        }
        return true;
    }
}
