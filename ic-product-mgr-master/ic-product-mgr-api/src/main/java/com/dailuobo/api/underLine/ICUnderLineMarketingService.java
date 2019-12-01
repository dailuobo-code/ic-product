package com.dailuobo.api.underLine;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.soa.SOAUnStoreMarketingInventory;
import com.dailuobo.api.domain.vo.UndProductVo;

import java.util.List;
import java.util.Map;

public interface ICUnderLineMarketingService {
    ICResponse<List<SOAUnStoreMarketingInventory>> getStoreMarketingInventories(
            Map<String, Object> param);

    ICResponse<List<SOAUnStoreMarketingInventory>> getUnMarketingInventories(
            Map<String, Object> param);

    ICResponse<Integer> updateAvailable(Map<String, Object> param);

    ICResponse<Integer> insertUnMarketingInventory(Map<String, Object> param);

    ICResponse<Integer> countUnMarketingInventory(Map<String, Object> param);

    ICResponse<List<UndProductVo>> getCityProductTotalAvailable(List<UndProductVo> undProductVoList);
}
