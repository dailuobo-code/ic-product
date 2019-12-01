package com.dailuobo.api.underLine;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.UnsaleCount;

import java.util.List;
import java.util.Map;

/**
 * 线下售卖  full-copy newmgr
 */
public interface ICUnderLineSaleService {
    ICResponse pickupLog(int cityId, int storeId, int userId, Integer[] proArr, Integer[] weightArr,
                         String[] productNos, String[] barcodes, String orderNo);

    ICResponse<List<UnsaleCount>> selectCount(Map<String, Object> param);

    ICResponse<Integer> getCityProduct(Map<String, Object> param);

    ICResponse<List<UnsaleCount>> selectAllStoreCount(Map<String, Object> param);

    ICResponse<String> checkProductStatus(Map<String, Object> param);

}
