package com.dailuobo.biz.manager.product;

import java.util.List;

/**
 * 数据缓存接口
 * @author wangshifeng
 * @date 2019-08-08 21:03
 */
public interface ProductCacheManager {
    /**
     * 城市下所有门店产销预测商品Id列表
     * @param cityId
     * @return
     */
    List<Integer> getTodayCityForecastProductIds(Integer cityId);

    /**
     * 添加
     * @param cityId
     * @param productIds
     */
    void addTodayCityForecastProductIds(Integer cityId, List<Integer> productIds);

    /**
     * 门店产销预测商品Id列表
     * @param storeId
     * @return
     */
    List<Integer> getTodayStoreForecastProductIds(Integer storeId);
}
