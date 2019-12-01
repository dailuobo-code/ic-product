package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.HqProductTax;
import com.dailuobo.api.domain.vo.HqProductCheckVo;
import com.dailuobo.ic.api.product.bean.request.RefreshClassifyProductRequest;
import com.dailuobo.ic.api.request.product.HqProductLocalizeRequest;
import com.dailuobo.ic.api.request.product.HqProductSyncExtraAttrRequest;

import java.util.List;
import java.util.Map;

public interface MgrHqProductForCityService {

    ICResponse<List<CityProduct>> selectHqAll(Integer cityId, Integer offset, Integer limit, Integer classifyId, Integer parentClassifyId, String productNo,
                                              String hqProductName, String status,
                                              Integer goodsTypeSearch);

    @Deprecated
    ICResponse<Boolean> localize(Map<String, Object> param);

    ICResponse<Boolean> localizeV2(HqProductLocalizeRequest localizeRequest);

    ICResponse<List<CityProduct>> getCityProductNosList(Map<String, Object> param);

    ICResponse<Boolean> updateProductClassify(Integer oldSencondClassifyId);

    /**
     *
     * @param request 刷新分类请求
     * @return
     */
    ICResponse<Boolean> refreshClassifyProductCache(RefreshClassifyProductRequest request);

    ICResponse<List<HqProductTax>> getProductTaxByCode(List<String> taxCode);

    ICResponse<HqProductCheckVo> checkHqProductNotExist(List<String> productNos, List<String> hqProductNames);

    /**
     * 总部商品修改商品属性 同步 到各个分城
     */
    ICResponse<Boolean> syncHqProductExtraAttr(HqProductSyncExtraAttrRequest attrRequest);

}
