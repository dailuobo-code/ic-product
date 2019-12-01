package com.mallcai.biz.product.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.biz.product.service.ItemWriteService;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemCreateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemDeleteRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class ItemWriteServiceTest extends BaseTransactionalTest {
    @Autowired
    private ItemWriteService itemWriteService;


    private static final String jsonPath = "data/api/input";

    protected String loadJson(String name) throws IOException {
        String path = jsonPath;
        if (!name.startsWith("/")) {
            path += "/";
        }
        path += name;
        if (!name.toLowerCase().endsWith(".json")) {
            path += ".json";
        }
        return Resources.toString(Resources.getResource(path), Charsets.UTF_8);
    }

    @Test
    public void create() throws IOException {
        String request = loadJson("itemcreaterequest");
        ItemCreateRequest rpcReq = JSON.parseObject(request, ItemCreateRequest.class);
        ICResponse<Long> longICResponse = itemWriteService.create(rpcReq);
        Assert.assertTrue(longICResponse.isSuccess());
    }

    @Test
    public void update() throws IOException {

        String request = loadJson("itemcreaterequest");
        ItemUpdateRequest rpcReq = JSON.parseObject(request, ItemUpdateRequest.class);
        rpcReq.getItemDetailParam().setItemId(3L);
        ICResponse<Boolean> update = itemWriteService.update(rpcReq);
        Assert.assertTrue(update.getResult());
    }

    @Test
    public void delete() {
        ItemDeleteRequest itemDeleteRequest = new ItemDeleteRequest();
        itemDeleteRequest.setSellerId(1L);
        List<IdVersionPair> idVersionPairs = Lists.newArrayList();
        IdVersionPair pair = new IdVersionPair();
        pair.setId(1L);
        pair.setVersion(1);
        idVersionPairs.add(pair);
        itemDeleteRequest.setTargetList(idVersionPairs);
        ICResponse<Boolean> delete = itemWriteService.delete(itemDeleteRequest);
        assertNotNull(delete);
        assertTrue(delete.isSuccess());
        assertTrue(delete.getResult());
    }

    @Test
    public void sellerUpdateStatus() {
    }
}
