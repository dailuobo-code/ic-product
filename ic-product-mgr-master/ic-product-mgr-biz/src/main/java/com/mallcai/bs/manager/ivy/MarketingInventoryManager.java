package com.mallcai.bs.manager.ivy;


import com.dailuobo.api.domain.entity.MarketingInventory2;
import com.dailuobo.api.domain.ivy.IVYMarketingStockQueryDTO;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.mapper.MarketingInventoryMapper2;
import com.mallcai.bs.model.ivy.IvyMarketingStockPageQueryDO;
import com.mallcai.bs.model.ivy.MarketingInventoryDO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MarketingInventoryManager {

    @Resource
    private MarketingInventoryMapper2 marketingInventoryMapper2;


    /**
     * 根据 商品id 仓id 或门店 id 查找商品实物库存
     */
    public List<MarketingInventory2> getMarketingInventory(IVYMarketingStockQueryDTO ivyMarketingStockQueryDTO) {
        List<MarketingInventoryDO> marketingInventoryDOS = marketingInventoryMapper2.listAll(
            IvyMarketingStockPageQueryDO.builder().cityProductIdList(ivyMarketingStockQueryDTO.getCityProductIdList())
                .storeId(ivyMarketingStockQueryDTO.getStoreId())
                .warehouseId(ivyMarketingStockQueryDTO.getWarehouseId())
                .cityId(ivyMarketingStockQueryDTO.getCityId())
                .limit(ivyMarketingStockQueryDTO.getPageSize())
                .offset((ivyMarketingStockQueryDTO.getCurrentPage() - 1) * ivyMarketingStockQueryDTO.getPageSize())
                .build());
        return OrikaUtil.convertList(marketingInventoryDOS, MarketingInventory2.class);
    }

    public Integer countMarketingInventory(IVYMarketingStockQueryDTO ivyMarketingStockQueryDTO) {
        return marketingInventoryMapper2.countAll(IvyMarketingStockPageQueryDO.builder().cityProductIdList(ivyMarketingStockQueryDTO.getCityProductIdList())
                .storeId(ivyMarketingStockQueryDTO.getStoreId())
                .warehouseId(ivyMarketingStockQueryDTO.getWarehouseId())
                .cityId(ivyMarketingStockQueryDTO.getCityId())
                .build());
    }

    /**
     * 修改安全库存
     */
    public Integer updateStockMarketingInventory(Integer id, Integer threshold) {
        MarketingInventoryDO marketingInventoryDO = new MarketingInventoryDO();
        marketingInventoryDO.setId(id);
        marketingInventoryDO.setThreshold(threshold);
        return marketingInventoryMapper2.updateTblMarketingInventoryByPrimaryKey(marketingInventoryDO);
    }

}
