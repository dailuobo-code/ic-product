package com.dailuobo.api.inventory;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.MarketingInventory2;
import com.dailuobo.api.domain.entity.WhMarketingInventory;

import java.util.List;
import java.util.Map;

public interface ICMarketingInventoryService2 {

    ICResponse<List<WhMarketingInventory>> getWhMarketingInventories(Map<String, Object> param);

    ICResponse<List<MarketingInventory2>> getMarketingInventories(Integer cityId, Integer cityProductId);
}
