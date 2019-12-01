package com.mallcai.bs.mapper.ivy;

import com.mallcai.bs.model.ivy.IvyStoreSkuInventoryDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IvyStoreSkuInventoryMapper {

    List<IvyStoreSkuInventoryDO> getStoreSkuInventory(@Param("marketingInventoryId") Integer marketingInventoryId, @Param("cityProductId") Integer cityProductId);

    Integer updateIVYStoreSkuInventory(IvyStoreSkuInventoryDO ivyStoreSkuInventoryDO);
}
