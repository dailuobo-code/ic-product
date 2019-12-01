package com.dailuobo.ic.api.request.category.front;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

/**
 * 城市级别前台类目关联的商品
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FCategoryRelatedCityProduct extends CityBaseRequest {

    /**
     * 主键id
     */
    private BigInteger id;
    /**
     * 前台类目跟商品关联关系
     */
    private BigInteger relationId;

    /**
     * 城市商品id
     */
    private Integer cityProductId;
    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 展示顺序
     */
    private Integer disPlayOrder = 1;


    @Override
    public boolean checkParams() {
        if (relationId == null || cityProductId == null || productNo == null || disPlayOrder == null) {
            throw new IllegalArgumentException("参数异常");
        }
        return true;
    }
}
