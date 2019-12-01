package com.mallcai.api.product.backend;


import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.FrozenProductDTO;
import com.mallcai.domain.product.request.GetFrozenProductRequest;

import java.util.List;

/**
 * 合伙人需求接口
 * @author tianjunwei
 * @Date 2019-09-02
 */
public interface PartnerProductService {

    /**
     * 获取冻品商品（业务汇总表tbl_city_product、tbl_hq_product），直接数据库查询，需要分页查询
     * @param request
     * @return
     */
    ICResponse<List<FrozenProductDTO>> getFrozenProduct(GetFrozenProductRequest request);

}
