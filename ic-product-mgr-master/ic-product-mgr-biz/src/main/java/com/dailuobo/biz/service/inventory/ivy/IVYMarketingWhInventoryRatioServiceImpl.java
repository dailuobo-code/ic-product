package com.dailuobo.biz.service.inventory.ivy;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYWarehouseCityInventoryRatioDTO;
import com.dailuobo.api.inventory.ivy.IVYMarketingWhInventoryRatioService;
import com.github.pagehelper.Page;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.manager.ivy.IVYMarketingWhInventoryRatioManager;
import com.mallcai.bs.model.ivy.IvyWarehouseInventoryRatioDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tianjunwei
 */
@Slf4j
@Service("iVYMarketingWhInventoryRatioService")
public class IVYMarketingWhInventoryRatioServiceImpl implements IVYMarketingWhInventoryRatioService {


    @Resource
    private IVYMarketingWhInventoryRatioManager ivyMarketingWhInventoryRatioManager;

    /**
     * 获取某个仓下的所有城市库存比例列表
     *
     * @param warehouseId
     * @param pageDTO     分页信息
     * @return
     */
    @Override
    public ICResponse<List<IVYWarehouseCityInventoryRatioDTO>> getWarehouseCityInventoryRatio(Integer warehouseId, PageDTO pageDTO) {

        try {
            if(warehouseId == null){
               return ICResponse.fail("仓库id为空");
            }
            if(pageDTO == null){
                pageDTO = new PageDTO();
                pageDTO.setPageSize(10);
                pageDTO.setCurrentPage(1);
            }
            Page<IvyWarehouseInventoryRatioDO> page = ivyMarketingWhInventoryRatioManager.getWarehouseCityInventoryRatio(warehouseId,pageDTO);
            if(page == null){
                return ICResponse.success(new ArrayList<>(),pageDTO);
            }else {
                List<IVYWarehouseCityInventoryRatioDTO> inventoryRatioDTOS = OrikaUtil.convertList(page.getResult(), IVYWarehouseCityInventoryRatioDTO.class);
                pageDTO.setTotalNumber(page.getTotal());
                return ICResponse.success(inventoryRatioDTOS,pageDTO);
            }
        }catch (Exception e){
            log.error("getWarehouseCityInventoryRatio error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 新增某个仓下的城市库存比例
     *
     * @param warehouseCityInventoryRatio
     * @return
     */
    @Override
    public ICResponse<IVYWarehouseCityInventoryRatioDTO> addWarehouseCityInventoryRatio(IVYWarehouseCityInventoryRatioDTO warehouseCityInventoryRatio) {

        try {

            if(warehouseCityInventoryRatio == null){
                return ICResponse.fail("参数为空");
            }
            if(warehouseCityInventoryRatio.getCityId() == null || warehouseCityInventoryRatio.getCityId() < 1){
                return ICResponse.fail("cityId 为空");
            }

            if(warehouseCityInventoryRatio.getWarehouseId() == null || warehouseCityInventoryRatio.getWarehouseId() < 1){
                return ICResponse.fail("仓id 为空");
            }
            IvyWarehouseInventoryRatioDO ivyWarehouseInventoryRatioDO = OrikaUtil.convert(warehouseCityInventoryRatio, IvyWarehouseInventoryRatioDO.class);
            ivyMarketingWhInventoryRatioManager.addWarehouseCityInventoryRatio(ivyWarehouseInventoryRatioDO);
            warehouseCityInventoryRatio = OrikaUtil.convert(ivyWarehouseInventoryRatioDO, IVYWarehouseCityInventoryRatioDTO.class);
            return ICResponse.success(warehouseCityInventoryRatio);
        }catch (Exception e){
            log.error("addWarehouseCityInventoryRatio error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 更新某个仓下的城市库存比例
     *
     * @param warehouseCityInventoryRatio
     * @return
     */
    @Override
    public ICResponse<Boolean> updateWarehouseCityInventoryRatio(IVYWarehouseCityInventoryRatioDTO warehouseCityInventoryRatio) {

        try {
            if(warehouseCityInventoryRatio == null){
                return ICResponse.fail("参数为空");
            }
            if(warehouseCityInventoryRatio.getId() == null || warehouseCityInventoryRatio.getId() < 1){
                return ICResponse.fail("ID 为空");
            }
            IvyWarehouseInventoryRatioDO ivyWarehouseInventoryRatioDO = OrikaUtil.convert(warehouseCityInventoryRatio, IvyWarehouseInventoryRatioDO.class);
            ivyMarketingWhInventoryRatioManager.updateWarehouseCityInventoryRatio(ivyWarehouseInventoryRatioDO);
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("updateWarehouseCityInventoryRatio error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 删除某个仓下的城市库存比例
     *
     * @param warehouseCityInventoryRatio
     * @return
     */
    @Override
    public ICResponse<Boolean> delWarehouseCityInventoryRatio(IVYWarehouseCityInventoryRatioDTO warehouseCityInventoryRatio) {
        try {
            if(warehouseCityInventoryRatio == null){
                return ICResponse.fail("不存在数据");
            }
            ivyMarketingWhInventoryRatioManager.delWarehouseCityInventoryRatio(warehouseCityInventoryRatio.getId());
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("delWarehouseCityInventoryRatio error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }
}
