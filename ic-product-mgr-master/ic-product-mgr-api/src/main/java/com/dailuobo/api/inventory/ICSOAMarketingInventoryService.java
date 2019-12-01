package com.dailuobo.api.inventory;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.dailuobo.api.domain.soa.SOAMarketingInventoryVo;
import com.dailuobo.api.domain.soa.SOAStoreProduct;

import java.util.List;
import java.util.Map;

public interface ICSOAMarketingInventoryService {

    ICResponse<Map<Integer, Boolean>> isAvailable(Map<SOAStoreProduct, Integer> map);

    ICResponse setMarketingInventoryV2(SOAMarketingInventoryVo miv);

    ICResponse setMarketingInventoryShare(Integer cityProductId, Integer available, Integer threshold, Integer type, Integer delta, Integer updateUserId);

    ICResponse setMarketingInventoryShareWarehouse(StoreMarketingInventory inventory);

    ICResponse setMarketingInventory(Integer cityProductId, Integer orgId, Integer delta, Integer threshold, Integer source) throws Exception;

    ICResponse deleteMarketingInventory(List<Integer> cityProductIds);

    ICResponse marketingInit(boolean flag, Integer cityId);

    ICResponse setMarketingInventoryTask(Integer cityProductId, Integer orgId, Integer delta);

    ICResponse checkPiece(Integer cityProductId, Integer delta, Integer cityId);

}
