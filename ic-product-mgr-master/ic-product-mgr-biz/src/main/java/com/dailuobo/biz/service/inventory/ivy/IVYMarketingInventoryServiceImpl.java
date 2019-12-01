package com.dailuobo.biz.service.inventory.ivy;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.MarketingInventory2;
import com.dailuobo.api.domain.ivy.IVYMarketingInventoryServiceDTO;
import com.dailuobo.api.domain.ivy.IVYMarketingStockQueryDTO;
import com.dailuobo.api.inventory.ivy.IVYMarketingInventoryService;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.manager.ivy.MarketingInventoryManager;
import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("iVYMarketingInventoryService")
public class IVYMarketingInventoryServiceImpl implements IVYMarketingInventoryService {


    @Resource
    private MarketingInventoryManager marketingInventoryManager;

    /**
     * 根据 商品id 仓id 或门店 id 查找商品实物库存
     */
    @Override
    public ICResponse<List<IVYMarketingInventoryServiceDTO>> getMarketingInventory(
        IVYMarketingStockQueryDTO ivyMarketingStockQueryDTO) {

        try {
            Integer total = marketingInventoryManager.countMarketingInventory(ivyMarketingStockQueryDTO);
            List<MarketingInventory2> marketingInventory2s = marketingInventoryManager.getMarketingInventory(
                ivyMarketingStockQueryDTO);
            List<IVYMarketingInventoryServiceDTO> ivyMarketingInventoryServiceDTOS = OrikaUtil
                .convertList(marketingInventory2s, IVYMarketingInventoryServiceDTO.class);
            ICResponse<List<IVYMarketingInventoryServiceDTO>> response = ICResponse
                .success(ivyMarketingInventoryServiceDTOS);
            PageDTO pageDTO = new PageDTO(ivyMarketingStockQueryDTO.getPageSize(), total,
                ivyMarketingStockQueryDTO.getCurrentPage());
            response.setPage(pageDTO);
            return response;
        } catch (Exception e) {
            log.error("getMarketingInventory error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 修改安全库存
     */
    @Override
    public ICResponse<Boolean> updateStockMarketingInventory(Integer id, Integer threshold) {

        try {
            marketingInventoryManager.updateStockMarketingInventory(id, threshold);
            return ICResponse.success(Boolean.TRUE);
        } catch (Exception e) {
            log.error("updateStockMarketingInventory error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }
}
