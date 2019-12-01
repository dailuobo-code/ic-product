package com.dailuobo.api.inventory.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYStoreSkuInventoryDTO;

import java.util.List;

/**
 *
 * 门店营销库存
 * @author tianjunwei
 * @date 2019-09-18
 */
public interface IVYStoreSkuInventoryService {

    /**
     * 获取仓下的门店的营销库存
     * @param marketingInventoryId
     * @param cityProductId
     * @param pageDTO
     * @return
     */
    ICResponse<List<IVYStoreSkuInventoryDTO>> getStoreSkuInventory(Integer marketingInventoryId, Integer cityProductId, PageDTO pageDTO);

}
