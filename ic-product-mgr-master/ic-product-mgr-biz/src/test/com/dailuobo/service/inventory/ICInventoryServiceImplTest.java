package com.dailuobo.service.inventory;

import com.dailuobo.BaseTest;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.MarketingInventory;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.dailuobo.api.inventory.ICMarketingInventoryService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ICInventoryServiceImplTest extends BaseTest {

    @Autowired
    private ICMarketingInventoryService marketingInventoryService;

    @Test
    public void getStoreMarketingInventories() {
        Map<String,Object> map = Maps.newHashMap();
        map.put("classifyId",401);
        map.put("isShare",1);
        ICResponse<List<StoreMarketingInventory>> storeMarketingInventories = marketingInventoryService.getStoreMarketingInventories(map);

    }

    @Test
    public void getMarketingInventoriesTest() {
        ICResponse<List<MarketingInventory>> marketingInventoryRespone = marketingInventoryService.getMarketingInventories(30,270144) ;
        List<MarketingInventory> marketingInventoryList = marketingInventoryRespone.getData() ;
        for (MarketingInventory marketingInventory : marketingInventoryList) {
            System.out.println("getCityProductId" + marketingInventory.getCityProductId());
            System.out.println("getInQuantity" + marketingInventory.getInQuantity());
        }
    }

    @Test
    public void getWareHouseMarketingSharesTest() {
        ICResponse<List<MarketingInventory>> marketingInventoryRespone = marketingInventoryService.getWareHouseMarketingShares(0,462,30) ;
        List<MarketingInventory> marketingInventoryList = marketingInventoryRespone.getData() ;
        for (MarketingInventory marketingInventory : marketingInventoryList) {
            System.out.println("getCityProductId" + marketingInventory.getCityProductId());
            System.out.println("getInQuantity" + marketingInventory.getInQuantity());
        }
    }

    @Test
    public void getMarketingInventories() {
    }

    @Test
    public void getMarketingShares() {
    }

    @Test
    public void getWareHouseMarketingShares() {
    }

    @Test
    public void getCityProductWareHouseTypeByCityProductId() {
    }

    @Test
    public void getCityProductWareHouseType() {
    }

    @Test
    public void updateMarketingShares() {
    }

    @Test
    public void checkProduct() {
    }

    @Test
    public void checkProductPiece() {
    }

    @Test
    public void updateMarketLog() {
    }

    @Test
    public void updateBannerProductOrder() {
    }

    @Test
    public void updateTileProductOrder() {
    }

    @Test
    public void updateThemeProductOrder() {
    }

    @Test
    public void getBannerIdsByProductId() {
    }

    @Test
    public void getTileIdsByProductId() {
    }

    @Test
    public void getThemeIdsByProductId() {
    }

    @Test
    public void queryNoIsshareProduct() {
    }

    @Test
    public void queryCityProductId() {
    }

    @Test
    public void manipulationData() {
    }

    @Test
    public void refreshMarketing() {
    }

    @Test
    public void saveImportLog() {
    }
}