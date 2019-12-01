package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreMarketingInventoryWareHouse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer cityProductId;
    private Integer cityId;
    private Integer isShare;
    List<StoreMarketingInventory> warehouse;
}
