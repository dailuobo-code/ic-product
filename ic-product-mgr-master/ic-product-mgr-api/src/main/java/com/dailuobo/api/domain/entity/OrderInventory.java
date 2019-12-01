package com.dailuobo.api.domain.entity;

import lombok.Data;

@Data
public class OrderInventory {
    /**
     * 城市商品id
     */
    private Integer cityProductId;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 商品售卖数量
     */
    private Integer productNum;
}
