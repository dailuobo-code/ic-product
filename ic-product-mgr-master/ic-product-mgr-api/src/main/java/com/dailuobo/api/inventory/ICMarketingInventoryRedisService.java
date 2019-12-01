package com.dailuobo.api.inventory;

import com.dailuobo.api.common.ICResponse;

import java.util.List;
import java.util.Map;

public interface ICMarketingInventoryRedisService {

    ICResponse deleteInventoryByCityProductIds(List<Integer> cityProductIdList);

    ICResponse refreshInventoryByCityProductIds(List<Integer> cityProductIdList);

    ICResponse refreshInventoryByCityProductIdsFromOld(List<Integer> cityProductIdList);

    ICResponse<Map<Integer,Boolean>> judgeCityProductIsNeedWarehouse(List<Integer> cityProductIdList);

    ICResponse<Map<Integer,Boolean>> judgeCityProductIsNeedWarehouseOld(List<Integer> cityProductIdList);

    ICResponse<Map<Integer,Boolean>> judgeCityProductIsAllowSetShare(List<Integer> cityProductIdList);

    ICResponse<Boolean> judgeCityIsHaveMoreWarehouse(Integer cityId);
}
