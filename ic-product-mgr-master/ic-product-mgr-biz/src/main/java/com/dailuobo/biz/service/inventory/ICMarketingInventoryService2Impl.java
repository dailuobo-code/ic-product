package com.dailuobo.biz.service.inventory;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.MarketingInventory2;
import com.dailuobo.api.domain.entity.WhMarketingInventory;
import com.dailuobo.api.inventory.ICMarketingInventoryService2;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.MarketingInventoryService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("iCMarketingInventoryService2")
public class ICMarketingInventoryService2Impl implements ICMarketingInventoryService2 {
    @Autowired
    private MarketingInventoryService2 marketingInventoryService2;

    @Transactional
    public ICResponse<List<WhMarketingInventory>> getWhMarketingInventories(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            int offset = 0;
            int limit = 0;
            if (param.get("offset") != null && param.get("limit") != null) {
                offset = (int) param.get("offset");
                limit = (int) param.get("limit");
                PageHelper.offsetPage(offset, limit);
            }
            try {
                List<WhMarketingInventory> whMarketingInventories = marketingInventoryService2.getWhMarketingInventories(param);
                if (offset >= 0 && limit > 0) {
                    PageDTO pageDTO = new PageDTO(limit, (int) ((Page) whMarketingInventories).getTotal(), offset / limit + 1);
                    return ICResponse.success(whMarketingInventories, pageDTO);
                }
                return ICResponse.success(whMarketingInventories);
            } catch (Exception ex) {
                log.error(String.format("getWhMarketingInventories error,request:%s", JSON.toJSONString(param)), ex);
                return ICResponse.fail(ex.getMessage());
            }
        }, "getWhMarketingInventories", param);

    }

    @Transactional
    public ICResponse<List<MarketingInventory2>> getMarketingInventories(Integer cityId, Integer cityProductId) {
        Map param1= Maps.newHashMap();
        param1.put("cityProductId",cityProductId);
        param1.put("cityId",cityId);

        return ICResponseHandler.template(() -> {
            List<MarketingInventory2> marketingInventories = marketingInventoryService2.getMarketingInventories(cityId, cityProductId);
            return ICResponse.success(marketingInventories);
        }, "getMarketingInventories", param1);
    }
}
