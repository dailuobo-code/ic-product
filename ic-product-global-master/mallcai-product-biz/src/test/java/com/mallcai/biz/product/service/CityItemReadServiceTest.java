package com.mallcai.biz.product.service;

import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.*;
import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CityItemReadServiceTest extends BaseTransactionalTest {

    @Autowired
    private CityItemReadService cityItemReadService;

    @Test
    public void paging() {
        CityItemPagingRequest request = new CityItemPagingRequest();
        request.setOffset(1);
        request.setLimit(10);
        //ICResponse<Paging<CityItemInfo>> paging = cityItemReadService.paging(request);
        /*printJson(paging);
        assertNotNull(paging);
        assertTrue(paging.isSuccess());*/
    }

    @Test
    public void queryFullCityItem() {
        FullCityItemQueryRequest fullItemQueryRequest = new FullCityItemQueryRequest();
        fullItemQueryRequest.setItemId(1L);
        fullItemQueryRequest.setCityId(30L);
        ICResponse<FullCityItemInfo> fullCityItemInfoICResponse = cityItemReadService.queryFullCityItem(fullItemQueryRequest);
        printJson(fullCityItemInfoICResponse);
        assertNotNull(fullCityItemInfoICResponse);
        assertTrue(fullCityItemInfoICResponse.isSuccess());

    }

    @Test
    public void queryFullCityItemWithDetail() {
        FullCityItemWithDetailQueryRequest request = new FullCityItemWithDetailQueryRequest();
        request.setItemId(1L);
        request.setCityId(30L);
        ICResponse<FullCityItemWithDetailInfo> fullCityItemWithDetailInfoICResponse = cityItemReadService.queryFullCityItemWithDetail(request);
        printJson(fullCityItemWithDetailInfoICResponse);
        assertNotNull(fullCityItemWithDetailInfoICResponse);
        assertTrue(fullCityItemWithDetailInfoICResponse.isSuccess());
    }

    @Test
    public void listItemCityRelations() {
        CityItemListRelationRequest request = new CityItemListRelationRequest();
        request.setItemIds(Lists.newArrayList(1L,2L,3L,4L));
        ICResponse<Map<Long, List<CityItemInfo>>> mapICResponse = cityItemReadService.listItemCityRelations(request);
        printJson(mapICResponse);
        assertNotNull(mapICResponse);
        assertTrue(mapICResponse.isSuccess());
    }
}