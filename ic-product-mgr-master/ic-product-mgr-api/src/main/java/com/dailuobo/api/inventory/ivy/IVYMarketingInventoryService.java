package com.dailuobo.api.inventory.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.ivy.IVYMarketingInventoryServiceDTO;
import com.dailuobo.api.domain.ivy.IVYMarketingStockQueryDTO;
import java.util.List;

/**
 * 商品实物库存  tbl_markeng_inventory 数据
 * @author tianjunwei
 * @date 2019-09-18
 */
public interface IVYMarketingInventoryService {


    /**
     * 根据 商品id 仓id 或门店 id 查找商品实物库存
     * @param ivyMarketingStockQueryDTOS
     * @return
     */
    ICResponse<List<IVYMarketingInventoryServiceDTO>> getMarketingInventory(
        IVYMarketingStockQueryDTO ivyMarketingStockQueryDTOS);

    /**
     * 修改安全库存
     * @param id
     * @param threshold
     * @return
     */
    ICResponse<Boolean> updateStockMarketingInventory(Integer id , Integer threshold);

}
