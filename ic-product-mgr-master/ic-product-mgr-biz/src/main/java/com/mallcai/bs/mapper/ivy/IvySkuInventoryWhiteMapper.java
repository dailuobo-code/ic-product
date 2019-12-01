package com.mallcai.bs.mapper.ivy;

import com.mallcai.bs.model.ivy.IvySkuInventoryWhiteDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IvySkuInventoryWhiteMapper {


    Integer addWarehouseSkuWhite(List<IvySkuInventoryWhiteDO> ivySkuInventoryWhiteDOS);

    Integer updateWarehouseSkuWhite(IvySkuInventoryWhiteDO ivyWarehouseSkuWhite);


    List<IvySkuInventoryWhiteDO> getWarehouseSkuWhite(List<IvySkuInventoryWhiteDO> ivySkuInventoryWhiteDOS);


    Integer delWarehouseSkuWhite(List<Integer> ids);

}
