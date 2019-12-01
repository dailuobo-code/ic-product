package com.mallcai.biz.inventory.dao.mapper;

import com.mallcai.biz.inventory.model.StoreWarehouseDO;
import com.mallcai.biz.inventory.model.WarehouseDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: chengxi
 * @Date: 2019-05-28 19:47
 * @Version 1.0
 */
public interface StoreWareHouseMapper {

    /**
     * 获取门店对应的仓
     * @param storeIds
     * @return
     */
    List<StoreWarehouseDO> getWareHouseByStoreIds(@Param("list") List<Integer> storeIds);

    /**
     * 城市下有对应的仓 不分类型
     *
     * @param cityId
     * @return
     */
    List<StoreWarehouseDO> getWareHouseByCityId(@Param("cityId") Integer cityId);



    void batchBindWarehouse(@Param("storeId") Integer storeId,
                            @Param("list") List<StoreWarehouseDO> list);


    void insertWarehouse(WarehouseDO warehouseDO);

    void updateWarehouse(WarehouseDO warehouseDO);

}
