package com.mallcai.bs.dao;

import com.mallcai.bs.mapper.CityWarehouseMapper;
import com.dailuobo.api.domain.vo.DDLWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CityWarehouseDao {
    @Autowired
    private CityWarehouseMapper cityWarehouseMapper;

    public List<DDLWarehouse> getDDLWarehouses(Integer cityId) {
        return cityWarehouseMapper.getDDLWarehouses(cityId);
    }

    public List<DDLWarehouse> getDDLWarehousesByType(Integer cityId,Integer wareHouseType) {
        return cityWarehouseMapper.getDDLWarehousesByType(cityId,wareHouseType);
    }

    public List<DDLWarehouse> getDDLWarehousesByIds(List<Integer> ids) {
        return cityWarehouseMapper.getDDLWarehousesByIds(ids);
    }

    public DDLWarehouse getDDLWarehouseByStoreId(Integer storeId){
        return cityWarehouseMapper.getDDLWarehouseByStoreId(storeId);
    }

    public List<DDLWarehouse> getDDLWarehouseByStoreIdList(List<Integer> storeIdList,Integer wareHouseType){
        return cityWarehouseMapper.getDDLWarehouseByStoreIdList(storeIdList,wareHouseType);
    }

    public Map<Integer,DDLWarehouse> getDDLWarehouseMapByStoreIdList(List<Integer> storeIdList,Integer wareHouseType){
        List<DDLWarehouse> list = getDDLWarehouseByStoreIdList(storeIdList,wareHouseType);
        Map<Integer,DDLWarehouse> resultMap = new HashMap<>();
        if(list != null && !list.isEmpty()){
            list.forEach(ddlWarehouse -> resultMap.put(ddlWarehouse.getStoreId(),ddlWarehouse));
        }
        return resultMap;
    }
}
