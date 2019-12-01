package com.dailuobo.biz.service.inventory.ivy;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYStoreSkuInventoryDTO;
import com.dailuobo.api.inventory.ivy.IVYStoreSkuInventoryService;
import com.github.pagehelper.Page;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.manager.ivy.IVYMarketingInventoryManager;
import com.mallcai.bs.manager.ivy.IVYStoreSkuInventoryManager;
import com.mallcai.bs.model.ivy.IvyMarketingInventoryDO;
import com.mallcai.bs.model.ivy.IvyStoreSkuInventoryDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author tianjunwei
 */
@Slf4j
@Service("iVYStoreSkuInventoryService")
public class IVYStoreSkuInventoryServiceImpl implements IVYStoreSkuInventoryService {

    @Resource
    private IVYStoreSkuInventoryManager ivyStoreSkuInventoryManager;
    @Autowired
    private IVYMarketingInventoryManager ivyMarketingInventoryManager;

    /**
     * 获取仓下的门店的营销库存
     *
     * @param marketingInventoryId
     * @param pageDTO
     * @return
     */
    @Override
    public ICResponse<List<IVYStoreSkuInventoryDTO>> getStoreSkuInventory(Integer marketingInventoryId, Integer cityProductId, PageDTO pageDTO) {

        try{
            if(pageDTO == null){
                pageDTO = new PageDTO();
                pageDTO.setPageSize(10);
                pageDTO.setCurrentPage(1);
            }
            IvyMarketingInventoryDO marketingInventoryDO = ivyMarketingInventoryManager
                .getSingleIVYMarketingInventory(marketingInventoryId);
            if(marketingInventoryDO == null){
                return ICResponse.fail("实物库存不存在");
            }
            Page<IvyStoreSkuInventoryDO> page
                = ivyStoreSkuInventoryManager.getStoreSkuInventory(marketingInventoryId,marketingInventoryDO.getCityProductId(),pageDTO);
            if(page == null){
                return ICResponse.success(new ArrayList<>());
            }else {
                pageDTO.setTotalNumber(page.getTotal());
                List<IVYStoreSkuInventoryDTO> ivyStoreSkuInventoryDTOS = OrikaUtil.convertList(page.getResult(),IVYStoreSkuInventoryDTO.class);
                ivyStoreSkuInventoryDTOS.forEach(ivyStoreSkuInventoryDTO -> {
                    ivyStoreSkuInventoryDTO.setMarketingNum(marketingInventoryDO.getMarketingNum());
                    ivyStoreSkuInventoryDTO.setActivityNum(marketingInventoryDO.getActivityNum());
                    ivyStoreSkuInventoryDTO.setPresellNum(marketingInventoryDO.getPresellNum());
                    ivyStoreSkuInventoryDTO.setAlarmNum(marketingInventoryDO.getAlarmNum());
                });
                return ICResponse.success(ivyStoreSkuInventoryDTOS, pageDTO);
            }
        }catch (Exception e){
            log.error("getWarehouseProductInventoryRatio error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }
}
