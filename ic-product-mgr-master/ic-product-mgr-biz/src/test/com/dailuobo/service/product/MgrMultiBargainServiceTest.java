package com.dailuobo.service.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.product.MgrCityProductService;
import com.mallcai.api.model.dubbo.IMerchantService;
import com.mallcai.api.model.merchant.OpMerchantDetailInfo;
import com.mallcai.api.model.merchant.OpMerchantDetailInfoLists;
import com.mallcai.backend.common.api.PlainResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class MgrMultiBargainServiceTest extends BaseTest {
    @Resource
    private MgrCityProductService mgrCityProductService;
    @Autowired
    private IMerchantService merchantService;

    Integer cityId =30;

    @Test
    public void testSelectAll() {
        //ICResponse<List<CityProduct>> listICResponse=mgrCityProductService.selectAll(0, 20, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        //System.out.println(JSON.toJSONString(listICResponse));
    }


    @Test
    public void testMerchant(){
        OpMerchantDetailInfo info = new OpMerchantDetailInfo();
        info.setCityId(cityId);
        PlainResult<OpMerchantDetailInfoLists> opMerchantDetailInfoListsPlainResult = merchantService.selectMerchantInfoLists(info);

        System.out.println(JSON.toJSONString(opMerchantDetailInfoListsPlainResult));
    }
}
