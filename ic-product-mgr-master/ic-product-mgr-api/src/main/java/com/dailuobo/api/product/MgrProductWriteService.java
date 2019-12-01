package com.dailuobo.api.product;

import com.dailuobo.ic.api.request.product.AsyncProductShelveRequest;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.mallcai.backend.common.api.PlainResult;

public interface MgrProductWriteService {

    /**
     * @param request 创建城市商品
     * @return
     */
    PlainResult<Boolean> createProduct(CityProductCreateRequest request);

    PlainResult<Boolean> asyncProductShelve(AsyncProductShelveRequest request);
}
