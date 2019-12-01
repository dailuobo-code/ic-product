package com.dailuobo.ic.api.spec;

import com.dailuobo.api.domain.entity.Spec;
import lombok.Data;

import java.io.Serializable;

@Data
public class SpecUpdateAsyncQueryRequest implements Serializable {
    /**
     * 城市Id
     */
    private Integer cityId;
    /**
     * skuId
     */
    private Long skuId;
    /**
     * itemId
     */
    private Long itemId;

    private Integer cityStatus;
    /**
     * 规格信息
     */
    private Spec spec;


    private String barCode;
    /**
     * 限购数量
     */
    private Integer threshold;

    /**
     * 划线价/市场价
     */
    private Double disablePrice;


    public void checkParams() {
        if (cityId == null) {
            throw new IllegalArgumentException("cityid is null");
        }
        if (skuId == null) {
            throw new IllegalArgumentException("skuId is null");
        }
        if (itemId == null) {
            throw new IllegalArgumentException("itemId is null");
        }
        if (spec == null) {
            throw new IllegalArgumentException("spec is null");
        }
    }
}
