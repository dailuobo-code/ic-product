package com.dailuobo.api.inventory.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYWarehouseCityInventoryRatioDTO;

import java.util.List;

/**
 * 仓库库存城市比例
 * @author tianjunwei
 * @date 2019-09-17
 */
public interface IVYMarketingWhInventoryRatioService {

    /**
     * 获取某个仓下的所有城市库存比例列表
     * @param warehouseId
     * @param pageDTO  分页信息
     * @return
     */
    ICResponse<List<IVYWarehouseCityInventoryRatioDTO>> getWarehouseCityInventoryRatio(Integer warehouseId, PageDTO pageDTO);

    /**
     * 新增某个仓下的城市库存比例
     * @param warehouseCityInventoryRatio
     * @return
     */
    ICResponse<IVYWarehouseCityInventoryRatioDTO>  addWarehouseCityInventoryRatio(IVYWarehouseCityInventoryRatioDTO warehouseCityInventoryRatio);


    /**
     * 更新某个仓下的城市库存比例
     * @param warehouseCityInventoryRatio
     * @return
     */
    ICResponse<Boolean>  updateWarehouseCityInventoryRatio(IVYWarehouseCityInventoryRatioDTO warehouseCityInventoryRatio);


    /**
     * 删除某个仓下的城市库存比例
     * @param warehouseCityInventoryRatio
     * @return
     */
    ICResponse<Boolean>  delWarehouseCityInventoryRatio(IVYWarehouseCityInventoryRatioDTO warehouseCityInventoryRatio);

}
