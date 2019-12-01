package com.dailuobo.service.inventory;

import com.dailuobo.BaseTest;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.dailuobo.api.domain.vo.MarketingVo;
import com.dailuobo.api.inventory.ICMarketingInventoryService;
import com.dailuobo.api.inventory.ICMarketingSearchService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ICInventorySearchServiceImplTest extends BaseTest {

    @Autowired
    private ICMarketingSearchService iCMarketingSearchService;

    @Test
    public void selectAll() {
        Map<String,Object> map = Maps.newHashMap();
        map.put("offset",0);
        map.put("limit",10);
        map.put("cityId",30) ;
        ICResponse<List<MarketingVo>> marketingVoList = iCMarketingSearchService.selectAll(map) ;
        System.out.println(marketingVoList.getData());
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