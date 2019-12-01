package com.dailuobo.biz.service.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.domain.dto.merchant.MerchantProductMarketDTO;
import com.dailuobo.api.domain.vo.CityProductVo;
import com.dailuobo.api.product.MgrCityProductService;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.product.service.MgrCityProductReadService;
import com.dailuobo.ic.api.request.product.MerchantProductMarketRequest;
import com.dailuobo.ic.api.request.product.ProductListQueryRequest;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.SOAStandardPage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class MgrCityProductServiceImplTest extends BaseTest {

    @Autowired
    private MgrCityProductService mgrCityProductService;

    @Autowired
    private MgrCityProductReadService mgrCityProductReadService;

    @Test
    public void getCityProductList() {

        ProductListQueryRequest request = new ProductListQueryRequest();
        request.setProductNo("99");
        request.setCityId(30);
        PlainResult<Paging<CityProductVo>> cityProductList = mgrCityProductService.getCityProductList(request);
        System.out.println(JSON.toJSONString(cityProductList));
    }

    @Test
    public void getSingleCityProductList() {
        SingleCityProductQueryRequest request = new SingleCityProductQueryRequest();
        request.setCityProductId(3);
        request.setCityId(30);
        PlainResult<CityProductVo> singleCityProductList = mgrCityProductService.getSingleCityProductList(request);
        System.out.println(JSON.toJSONString(singleCityProductList));
    }

    @Test
    public void getMerchantProductMarketPage() {
        MerchantProductMarketRequest marketRequest = MerchantProductMarketRequest.builder().cityId(30).merchantId(0).page(2).pageSize(20).productName("猪").build();
        PlainResult<SOAStandardPage<MerchantProductMarketDTO>> merchantProductMarketPage = mgrCityProductReadService.getMerchantProductMarketPage(marketRequest);
        System.out.println("getMerchantProductMarketPage>>"+JSON.toJSONString(merchantProductMarketPage));

        MerchantProductMarketRequest marketRequest1 = MerchantProductMarketRequest.builder().cityId(30).merchantId(0).cityProductIds(Lists.newArrayList(3,27,60,63,66,69,72)).build();
        PlainResult<List<MerchantProductMarketDTO>> merchantProductMarketList = mgrCityProductReadService.getMerchantProductMarketList(marketRequest1);
        System.out.println("getMerchantProductMarketList>>"+JSON.toJSONString(merchantProductMarketList));
    }
}