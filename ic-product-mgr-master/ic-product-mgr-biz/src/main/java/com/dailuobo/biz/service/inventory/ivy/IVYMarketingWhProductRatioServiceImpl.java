package com.dailuobo.biz.service.inventory.ivy;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYWarehouseProductInventoryRatioDTO;
import com.dailuobo.api.inventory.ivy.IVYMarketingWhProductRatioService;
import com.github.pagehelper.Page;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.manager.ivy.IVYMarketingWhProductRatioManager;
import com.mallcai.bs.model.ivy.IvyWhProductRatioDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 仓库库存商品比例
 * @author tianjunwei
 * @date 2019-09-17
 */
@Slf4j
@Service("iVYMarketingWhProductRatioService")
public class IVYMarketingWhProductRatioServiceImpl implements IVYMarketingWhProductRatioService {


    @Resource
    private IVYMarketingWhProductRatioManager ivyMarketingWhProductRatioManager;

    /**
     * 根据 城市id 和 城市库存比例 查找商品销售库存占比
     *
     * @param cityId
     * @param warehouseInventoryatioId
     * @param pageDTO
     * @return
     */
    @Override
    public ICResponse<List<IVYWarehouseProductInventoryRatioDTO>> getWarehouseProductInventoryRatio(Integer cityId, Integer warehouseInventoryatioId, PageDTO pageDTO) {

        try {

            if(pageDTO == null){
                pageDTO = new PageDTO();
                pageDTO.setCurrentPage(1);
                pageDTO.setPageSize(10);
            }
            if(pageDTO.getCurrentPage() < 1){
                pageDTO.setCurrentPage(1);
            }

            if(pageDTO.getPageSize() < 1){
                pageDTO.setPageSize(10);
            }
            Page<IvyWhProductRatioDO> page = ivyMarketingWhProductRatioManager.getWarehouseProductInventoryRatio(cityId,warehouseInventoryatioId,pageDTO);
            if(page == null){
                return ICResponse.success(new ArrayList<IVYWarehouseProductInventoryRatioDTO>());
            }else {
                pageDTO.setTotalNumber(page.getTotal());
                List<IVYWarehouseProductInventoryRatioDTO> inventoryRatioDTOS = OrikaUtil.convertList(page.getResult(),IVYWarehouseProductInventoryRatioDTO.class);
                return ICResponse.success( inventoryRatioDTOS,pageDTO);
            }
        }catch (Exception e){
            log.error("getWarehouseProductInventoryRatio error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }
}
