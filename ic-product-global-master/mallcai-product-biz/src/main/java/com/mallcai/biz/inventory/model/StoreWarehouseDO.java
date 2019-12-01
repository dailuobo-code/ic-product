package com.mallcai.biz.inventory.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: mengjia
 * @Date: 2019-05-28 19:34
 * @Version 1.0
 */
@Data
public class StoreWarehouseDO implements Serializable {
    private Integer id;
    private Integer storeId;
    private Integer warehouseId;
    private Integer type;

    private Integer storeWarehouseType;
    private String name;
    private Integer createUserId;

    private Integer cityId;
}
