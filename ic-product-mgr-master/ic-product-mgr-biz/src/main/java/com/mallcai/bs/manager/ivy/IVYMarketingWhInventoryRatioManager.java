package com.mallcai.bs.manager.ivy;


import com.dailuobo.api.common.PageDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.mapper.ivy.IvyWarehouseInventoryRatioMapper;
import com.mallcai.bs.model.ivy.IvyWarehouseInventoryRatioDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仓库存分配比例
 * @author tianjunwei
 * @date 2019-09-19
 */
@Service
public class IVYMarketingWhInventoryRatioManager {

    @Resource
    private IvyWarehouseInventoryRatioMapper ivyWarehouseInventoryRatioMapper;

    public Page<IvyWarehouseInventoryRatioDO> getWarehouseCityInventoryRatio(Integer warehouseId, PageDTO pageDTO){

        Page<IvyWarehouseInventoryRatioDO> page = PageHelper.startPage(pageDTO.getCurrentPage(),pageDTO.getPageSize());

        List<IvyWarehouseInventoryRatioDO> list = ivyWarehouseInventoryRatioMapper.getIvyWarehouseInventoryRatio(warehouseId);

        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer addWarehouseCityInventoryRatio(IvyWarehouseInventoryRatioDO warehouseCityInventoryRatio){
        return ivyWarehouseInventoryRatioMapper.addWarehouseCityInventoryRatio(warehouseCityInventoryRatio);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer updateWarehouseCityInventoryRatio(IvyWarehouseInventoryRatioDO warehouseCityInventoryRatio){
        return ivyWarehouseInventoryRatioMapper.updateWarehouseCityInventoryRatio(warehouseCityInventoryRatio);
    }

    @Transactional
    public Integer delWarehouseCityInventoryRatio(Integer id){
        return ivyWarehouseInventoryRatioMapper.delWarehouseCityInventoryRatio(id);
    }



}
