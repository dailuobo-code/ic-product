package com.dailuobo.api.nosale;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.StoreNoSale;
import com.dailuobo.api.domain.vo.NoSaleVo;

import java.util.List;
import java.util.Map;

public interface ShopNoSaleService {
    ICResponse<List<NoSaleVo>> selectAll(Map<String, Object> param);

    ICResponse add(List<StoreNoSale> storeNoSales);

    ICResponse delete(List<Integer> ids, Integer userId,Integer cityId,Integer[] storeIds);

    ICResponse updateCityStoreNoSaleProduct(Integer cityId, Integer storeId);
}
