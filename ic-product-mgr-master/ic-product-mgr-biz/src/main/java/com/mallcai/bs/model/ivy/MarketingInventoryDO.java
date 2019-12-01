package com.mallcai.bs.model.ivy;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: mengjia
 * @Date: 2019-05-14 13:42
 * @Version 1.0
 */
@Data
public class MarketingInventoryDO implements Serializable {

    private Integer id;

    private Integer cityProductId;

    private Integer available;

    private Integer version;

    private Integer storeId;
    /**
     * 仓Id
     */
    private Integer warehouseId;

    /**
     * 预警量
     */
    private Integer threshold;

    private Double totalWeight;

}
