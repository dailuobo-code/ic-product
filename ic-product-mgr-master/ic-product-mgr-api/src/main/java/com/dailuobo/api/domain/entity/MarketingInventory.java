package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Cowboy on 2016/4/13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MarketingInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer available;
    private Integer threshold;
    private Integer cityProductId;
    private Integer storeId;
    private String storeName;
    private Integer availableBase;
    private Integer warehouseId;
    private String warehouseName;
    /**
     * 在单量(2019-08-21新增)
     */
    private Integer inQuantity;

    /**
     * 版本(2019-09-21)
     */
    private Integer version;


}
