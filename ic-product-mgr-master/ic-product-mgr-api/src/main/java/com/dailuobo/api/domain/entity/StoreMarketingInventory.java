package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class StoreMarketingInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityProductId;
    private String productNo;
    private String hqProductName;
    private String showName;
    private String c1Name;
    private String c2Name;
    private String cityProductIcon;
    private Integer storeId;
    private String storeName;
    private Integer available;
    private Integer threshold;
    private Integer availableBase;
    private Integer isShare;
    private Integer cityId;
    private Integer delta;
    private Integer warehouseId;
    private Integer warehouseName;
    private Integer createUserId;
    private Integer updateUserId;

}
