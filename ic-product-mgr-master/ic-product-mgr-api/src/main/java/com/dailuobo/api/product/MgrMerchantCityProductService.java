package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;

import java.util.List;
import java.util.Map;

public interface MgrMerchantCityProductService {

    ICResponse<List<CityProduct>> selectAll(Integer cityId, Map<String, Object> param, Integer offset, Integer limit);
}
