package com.mallcai.bs.service;

import com.mallcai.bs.dao.MarketingInventoryDao2;
import com.dailuobo.api.domain.entity.MarketingInventory2;
import com.dailuobo.api.domain.entity.WhMarketingInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/4/13.
 */
@Service
public class MarketingInventoryService2 {
    @Autowired
    private MarketingInventoryDao2 marketingInventoryDao;

    @Transactional
    public List<WhMarketingInventory> getWhMarketingInventories(Map<String, Object> param) {
        return marketingInventoryDao.getWhMarketingInventories(param);
    }

    @Transactional
    public List<MarketingInventory2> getMarketingInventories(Integer cityId, Integer cityProductId) {
        return marketingInventoryDao.getMarketingInventories(cityId, cityProductId);
    }
}
