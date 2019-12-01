package com.dailuobo.ic.api.product.service;

import com.dailuobo.api.domain.dto.merchant.MerchantProductMarketDTO;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductSearchRequest;
import com.dailuobo.ic.api.request.product.MerchantProductMarketRequest;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.SOAStandardPage;

import java.util.List;

public interface MgrCityProductReadService {
    /**
     * 查询城市商品数据
     *
     * @param request
     * @return
     */
    PlainResult<List<CityProduct>> findCityProductByCityId(CityProductSearchRequest request);

    PlainResult<Paging<CityProduct>> productListByCity(CityProductQueryRequest request);

    /**
     * 商家后台、营销配置分页获取商品列表
     *
     * @param productMarketRequest
     * @return
     */
    PlainResult<SOAStandardPage<MerchantProductMarketDTO>> getMerchantProductMarketPage(MerchantProductMarketRequest productMarketRequest);


    /**
     * 商家后台、营销配置 商品id获取商品
     *
     * @param productMarketRequest
     * @return
     */
    PlainResult<List<MerchantProductMarketDTO>> getMerchantProductMarketList(MerchantProductMarketRequest productMarketRequest);

}
