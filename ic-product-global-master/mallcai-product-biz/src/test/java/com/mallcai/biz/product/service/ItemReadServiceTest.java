package com.mallcai.biz.product.service;

import com.alibaba.fastjson.JSON;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.ItemReadFacade;
import com.mallcai.domain.product.dto.ItemInfoDTO;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.Assert.*;

public class ItemReadServiceTest extends BaseTransactionalTest {
    @Autowired
    ItemReadService itemReadService;

    @Test
    public void paging() {
        ItemPagingRequest request = new ItemPagingRequest();
        request.setOffset(0);
        request.setLimit(10);
        request.setItemName("name");
        ICResponse<Paging<ItemInfoDTO>> paging = itemReadService.paging(request);
        Assert.assertTrue(paging.isSuccess());
        System.out.println(JSON.toJSONString(paging));
    }

    @Test
    public void queryFullItem() {
        FullItemQueryRequest request = new FullItemQueryRequest();
        request.setItemId(1L);
        ICResponse<FullItemInfo> fullItemInfoICResponse = itemReadService.queryFullItem(request);
        Assert.assertTrue(fullItemInfoICResponse.isSuccess());
        System.out.println(fullItemInfoICResponse);

    }

    @Test
    public void queryItemForEdit() {

        FullItemWithDetailQueryRequest request = FullItemWithDetailQueryRequest.builder()
                .itemId(1L).build();
        ICResponse<FullItemWithDetailInfo> fullItemWithDetailInfoICResponse =
                itemReadService.queryFullItemWithDetail(request);

        Assert.assertTrue(fullItemWithDetailInfoICResponse.isSuccess());
        System.out.println(JSON.toJSONString(fullItemWithDetailInfoICResponse));
    }

    @Test
    public void queryFullItemWithDetail() {
        FullItemQueryRequest request = new FullItemQueryRequest();
        request.setItemId(1L);
        ICResponse<FullItemInfo> fullItemInfoICResponse = itemReadService.queryFullItem(request);
        Assert.assertTrue(fullItemInfoICResponse.isSuccess());
        System.out.println(JSON.toJSONString(fullItemInfoICResponse));
    }
}