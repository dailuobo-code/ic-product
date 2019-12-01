package com.mallcai.bs.mapper.ivy;

import com.mallcai.bs.model.ivy.IvyMarketingInventoryDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IvyMarketingInventoryMapper {

    List<IvyMarketingInventoryDO> getIVYMarketingInventory(@Param("cityId") Integer cityId, @Param("cityProductId") Integer cityProductId);

    public Integer updateIVYMarketingInventory(IvyMarketingInventoryDO ivyWarehouseInventory);

    public Integer updateIVYMarketingInventoryAlarm(IvyMarketingInventoryDO ivyWarehouseInventory);

    public Integer updateIVYMarketingInventoryAvailable(IvyMarketingInventoryDO ivyWarehouseInventory);

    IvyMarketingInventoryDO selectByPrimaryKey(Integer id);

}
