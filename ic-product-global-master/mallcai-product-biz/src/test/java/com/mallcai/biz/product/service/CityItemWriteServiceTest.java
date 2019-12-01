package com.mallcai.biz.product.service;

import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.item.api.bean.request.item.CityBatchBindToItemRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateStatusRequest;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CityItemWriteServiceTest extends BaseTransactionalTest {

    @Autowired
    private CityItemWriteService cityItemWriteService;

    @Test
    public void batchBindCity() {
        CityBatchBindToItemRequest request = new CityBatchBindToItemRequest();
        request.setCityIds(Lists.newArrayList(220L, 30L));
        request.setItemId(1L);
        request.setUpdatedBy(100L);
        request.setSellerId(10L);
        ICResponse<Boolean> booleanICResponse = cityItemWriteService.batchBindCity(request);
        printJson(booleanICResponse);
        assertNotNull(booleanICResponse);
        assertTrue(booleanICResponse.isSuccess());
        assertTrue(booleanICResponse.getResult());
    }

    @Test
    public void update() {
        CityItemUpdateRequest request = new CityItemUpdateRequest();
        //request.setCityId(30L);
        request.setSellerId(10L);
        request.setUpdatedBy(100L);
        //request.setSkuParamList();
        ICResponse<Boolean> update = cityItemWriteService.update(request);
        printJson(update);
        assertNotNull(update);
        assertTrue(update.isSuccess());
        assertTrue(update.getResult());
    }

    @Test
    public void sellerUpdateStatus() {
        CityItemUpdateStatusRequest request = new CityItemUpdateStatusRequest();
        request.setCityId(30L);
        request.setSellerId(10L);
        request.setUpdatedBy(100L);
        request.setStatus(ItemStatus.DISABLE);
        List<IdVersionPair> idVersionPairs = Lists.newArrayList();
        IdVersionPair pair = new IdVersionPair();
        pair.setId(1L);
        pair.setVersion(1);
        idVersionPairs.add(pair);
        request.setTargetList(idVersionPairs);
        ICResponse<Boolean> booleanICResponse = cityItemWriteService.sellerUpdateStatus(request);
        printJson(booleanICResponse);
        assertNotNull(booleanICResponse);
        assertTrue(booleanICResponse.isSuccess());
        assertTrue(booleanICResponse.getResult());
    }
}