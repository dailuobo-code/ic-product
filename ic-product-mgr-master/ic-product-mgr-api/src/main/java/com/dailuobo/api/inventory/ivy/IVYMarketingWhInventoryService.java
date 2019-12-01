package com.dailuobo.api.inventory.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.ivy.IVYMarketingWhInventoryDTO;

import java.util.List;

/**
 *
 * 商品营销总库存
 * @author tianjunwei
 * @date 2019-09-18
 */
public interface IVYMarketingWhInventoryService {

    /**
     * 根据城市id 和商品id 查找商品所在仓 库存信息
     * @param cityId
     * @param cityProductId
     * @return
     */
    ICResponse<List<IVYMarketingWhInventoryDTO>>  getIVYWarehouseInventory(Integer cityId, Integer cityProductId);


    /**
     * 修改销售总库存
     * @return
     */
    ICResponse<Boolean> updateIVYWarehouseInventory(IVYMarketingWhInventoryDTO ivyWarehouseInventory);


    /**
     * 修改可售库存
     * @return
     */
    ICResponse<Boolean> updateIVYWarehouseInventoryAvailableNum(IVYMarketingWhInventoryDTO ivyWarehouseInventory);

    /**
     * 修改告警库存
     * @return
     */
    ICResponse<Boolean> updateIVYWarehouseInventoryAlarmNum(IVYMarketingWhInventoryDTO ivyWarehouseInventory);

}
