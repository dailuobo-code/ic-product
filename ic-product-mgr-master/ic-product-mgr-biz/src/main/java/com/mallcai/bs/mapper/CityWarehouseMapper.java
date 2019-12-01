package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.vo.DDLWarehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityWarehouseMapper {
    List<DDLWarehouse> getDDLWarehouses(@Param("cityId") Integer cityId);

    List<DDLWarehouse> getDDLWarehousesByType(@Param("cityId") Integer cityId,@Param("type") Integer wareHouseType);

    List<DDLWarehouse> getDDLWarehousesByIds(@Param("ids") List<Integer> ids);

    DDLWarehouse getDDLWarehouseByStoreId(@Param("storeId")Integer storeId);

    List<DDLWarehouse> getDDLWarehouseByStoreIdList(@Param("storeIds") List<Integer> storeIds,@Param("type") Integer wareHouseType);

    List<DDLWarehouse> getWareHouse(@Param("cityIds") List<Integer> cityIds, @Param("type") Integer type, @Param("status") Integer status,@Param("name") String name);

}
