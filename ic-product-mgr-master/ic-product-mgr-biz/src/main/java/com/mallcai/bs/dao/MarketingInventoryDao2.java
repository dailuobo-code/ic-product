package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.MarketingInventory2;
import com.dailuobo.api.domain.entity.WhMarketingInventory;
import com.mallcai.bs.mapper.MarketingInventoryMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/4/13.
 */
@Repository
public class MarketingInventoryDao2 {
    @Autowired
    private MarketingInventoryMapper2 marketingInventoryMapper;

    public List<WhMarketingInventory> getWhMarketingInventories(Map<String, Object> param) {
        return marketingInventoryMapper.getWhMarketingInventories(param);
    }

    public List<MarketingInventory2> getMarketingInventories(Integer cityId, Integer cityProductId) {
        return marketingInventoryMapper.getMarketingInventories(cityId, cityProductId);
    }
}
