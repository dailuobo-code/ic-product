package com.mallcai.biz.category.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.ProductPushService;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.domain.enums.ShelveOperationEnum;
import com.mallcai.domain.product.dto.SkuAttributeDTO;
import com.mallcai.domain.product.request.AsyProductRequest;
import com.mallcai.domain.product.request.CityProductStatusChangeRequest;
import com.mallcai.domain.product.request.ProductCreateAsyncRequest;
import com.mallcai.domain.product.request.ProductSpecModifyAsyncQueryRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductPushServiceImplTest extends BaseTransactionalTest {

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

    @Autowired
    private ProductPushService productPushService;

    @Test
    public void pushProduct() throws IOException {
        String itemReadItemInfo = loadJson("ItemReadItemInfo");
        FullItemWithDetailInfo detailInfo = JSON.parseObject(itemReadItemInfo, FullItemWithDetailInfo.class);
        ProductCreateAsyncRequest pushRequest = new ProductCreateAsyncRequest();
        pushRequest.setItemDetailInfo(detailInfo.getItemDetailInfo());
        pushRequest.setItemInfo(detailInfo.getItemInfo());
        // pushRequest.setSkuInfoList(detailInfo.getSkuInfoList());
        //PlainResult<Void> voidPlainResult = productPushService.productCreateAsync(pushRequest);
        //printJson(voidPlainResult);
    }

    @Test
    public void asyncProductSpec() {
        String specJson ="{\"cityId\":5,\"itemId\":56,\"skuUpdateDTOList\":[{\"barCode\":\"36554\",\"extraJson\":\"{\\\"salesType\\\":\\\"WEIGHT\\\",\\\"packageWeight\\\":\\\"799\\\",\\\"packageQuantity\\\":\\\"1\\\"}\",\"price\":127840,\"priceJSON\":\"{\\\"avgPrice\\\":80000,\\\"disablePrice\\\":10000}\",\"threshold\":0,\"updateBy\":14,\"updateStatus\":1},{\"barCode\":\"36555\",\"extraJson\":\"{\\\"salesType\\\":\\\"WEIGHT\\\",\\\"packageWeight\\\":\\\"799\\\",\\\"packageQuantity\\\":\\\"1\\\"}\",\"price\":127999,\"priceJSON\":\"{\\\"avgPrice\\\":80100,\\\"disablePrice\\\":10000}\",\"threshold\":0,\"updateBy\":14,\"updateStatus\":1},{\"barCode\":\"36556\",\"extraJson\":\"{\\\"salesType\\\":\\\"WEIGHT\\\",\\\"packageWeight\\\":\\\"799\\\",\\\"packageQuantity\\\":\\\"1\\\"}\",\"price\":128159,\"priceJSON\":\"{\\\"avgPrice\\\":80200,\\\"disablePrice\\\":10000}\",\"threshold\":0,\"updateBy\":14,\"updateStatus\":1},{\"barCode\":\"36557\",\"extraJson\":\"{\\\"salesType\\\":\\\"WEIGHT\\\",\\\"packageWeight\\\":\\\"799\\\",\\\"packageQuantity\\\":\\\"1\\\"}\",\"price\":128319,\"priceJSON\":\"{\\\"avgPrice\\\":80300,\\\"disablePrice\\\":10000}\",\"threshold\":0,\"updateBy\":14,\"updateStatus\":1}],\"updateUserId\":14}";
        ProductSpecModifyAsyncQueryRequest request =JSON.parseObject(specJson,ProductSpecModifyAsyncQueryRequest.class);
        PlainResult<Void> voidPlainResult = productPushService.asyncProductSpec(request);
    }

    @Test
    @Transactional
    public void asyCityProduct() {
        AsyProductRequest req = new AsyProductRequest();
        req.setCityIdList(Lists.newArrayList(666L));
        req.setItemIds(1L);
        req.setSkuId(Lists.newArrayList(1L,2L,3L,4L,5L,6L,7L,8L));
        productPushService.asyCityProduct(req);

    }

    @Test
    public void TestStatus() {
        CityProductStatusChangeRequest request = new CityProductStatusChangeRequest();
        request.setSellerId(100L);
        request.setStatus(ShelveOperationEnum.OFF);
        request.setItemId(64L);
        request.setCityId(30);
        request.setUserId(10);
        productPushService.updateCityProductStatus(request);
    }
}