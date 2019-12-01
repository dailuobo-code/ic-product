package com.dailuobo.api.inventory.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYWarehouseProductInventoryRatioDTO;

import java.util.List;

/**
 * 仓库库存商品比例
 * @author tianjunwei
 * @date 2019-09-17
 */
public interface IVYMarketingWhProductRatioService {


    /**
     * 根据 城市id 和 城市库存比例 查找商品销售库存占比
     * @param cityId
     * @param warehouseInventoryatioId
     * @return
     */
    ICResponse<List<IVYWarehouseProductInventoryRatioDTO>> getWarehouseProductInventoryRatio(Integer cityId, Integer warehouseInventoryatioId, PageDTO pageDTO);

}
