package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by Cowboy on 2016/4/13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MarketingInventory2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer available;
    private Integer threshold;
    private Integer cityProductId;
    private Integer warehouseId;
    private Integer storeId;
    private String warehouseName;
    private Integer availableBase;
}
