package com.dailuobo.api.specification;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.*;

import java.util.List;
import java.util.Map;

/**
 * 规格服务
 */
public interface SpecificationService {
    ICResponse<SpecAdjustment> getSpecAdjustment(Integer cityId, Integer storeId, Integer cityProductId);

    ICResponse<List<Spec>> getSpecs(Integer cityId, Integer cityProductId);

    ICResponse<Spec> getDefaultSpec(Integer cityProductId);

    ICResponse createOrUpdate(CityProduct cityProduct, Spec spec);


    ICResponse< List<StoreSpec>> getStoreSpecs(Map<String, Object> param);


    ICResponse updateDefaultSpec(CityProduct cityProduct, Spec spec);

    ICResponse updateAdjustmentFactor(Integer cityId, Integer storeId, Integer cityProductId, Integer unify, Double delta, Integer upperLimit, Integer lowerLimit, Integer userId);


    ICResponse updateDefaultVipCount(Map<String, Object> param);


    ICResponse<Spec> getDefaultSpecBySyncProduct(Integer cityProductId);


    ICResponse syncProductSpecCreate(Spec spec);


    ICResponse syncProductSpecUpdate(Spec spec);

    ICResponse updateSalesSpec(Map<String, Object> param);

    /**
     * 销售方式相关信息
     * @param saleTypeInfo <br/>
     * cityProductId NOTNULL <br/>
     * changeFlag NOTNULL <br/>
     * packageMaxWeight NOTNULL <br/>
     * packageQuantity 当 changeFlag == 2 时  NOTNULL <br/>
     * @return
     */
    ICResponse updateSaleTypeInfo(SaleTypeInfo saleTypeInfo);
}
