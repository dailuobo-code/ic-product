package com.mallcai.api.product.backend;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.domain.product.AsyncProductLogDTO;
import com.mallcai.domain.product.dto.ProductAsyLogDTO;
import com.mallcai.domain.product.request.*;

import java.util.List;

/**
 *
 */
public interface ProductPushService {


    /**
     * 商品绑定城市同步老系统
     *
     * @param request
     * @return
     */
    PlainResult<AsyncProductLogDTO> asyCityProduct(AsyProductRequest request);

    /**
     * 城市商品规格同步
     *
     * @param request 规格同步请求
     * @return
     */
    PlainResult<Void> asyncProductSpec(ProductSpecModifyAsyncQueryRequest request);


    /**
     * 商品上下架
     *
     * @return
     */
    PlainResult<Void> updateCityProductStatus(CityProductStatusChangeRequest request);


}
