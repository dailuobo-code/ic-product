package com.dailuobo.ic.service.spec;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.api.specification.SpecWriteService;
import com.dailuobo.ic.api.spec.SpecUpdateAsyncQueryRequest;
import com.mallcai.backend.common.api.PlainResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SpecWriteServiceImplTest extends BaseTest {

    @Autowired
    SpecWriteService specWriteService;

    @Test
    public void specUpdateAsy() {
        SpecUpdateAsyncQueryRequest request = new SpecUpdateAsyncQueryRequest();
        request.setBarCode("138191");
        request.setSkuId(1001L);
        request.setItemId(1002L);
        request.setCityId(30);
        request.setDisablePrice(100D);
        request.setThreshold(1);

        Spec spec = new Spec();
        spec.setAvgPrice(100D);
        spec.setRealPrice(300D);
        spec.setCityId(30);
        spec.setCityProductId(3);
        spec.setThreshold(1);
        spec.setUpdateUserId(111);
        request.setSpec(spec);
        String str ="{\"barCode\":\"36557\",\"cityId\":5,\"cityStatus\":2,\"disablePrice\":10000.0,\"itemId\":56,\"skuId\":142,\"spec\":{\"avgPrice\":803.0,\"packageMaxWeight\":799,\"packageQuantity\":1,\"realPrice\":1283.19,\"unitType\":1,\"updateUserId\":14},\"threshold\":0}";
        request = JSON.parseObject(str,SpecUpdateAsyncQueryRequest.class);
        PlainResult<Boolean> booleanPlainResult = specWriteService.specUpdateAsy(request);

    }
}