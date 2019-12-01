package com.mallcai.bs.mapper.ivy;

import com.mallcai.bs.model.ivy.IvyWarehouseInventoryRatioDO;

import java.util.List;

public interface IvyWarehouseInventoryRatioMapper {


    List<IvyWarehouseInventoryRatioDO> getIvyWarehouseInventoryRatio(Integer warehouseId);

    Integer addWarehouseCityInventoryRatio(IvyWarehouseInventoryRatioDO ivyWarehouseInventoryRatioDO);

    Integer updateWarehouseCityInventoryRatio(IvyWarehouseInventoryRatioDO ivyWarehouseInventoryRatioDO);

    Integer delWarehouseCityInventoryRatio(Integer id);
}
