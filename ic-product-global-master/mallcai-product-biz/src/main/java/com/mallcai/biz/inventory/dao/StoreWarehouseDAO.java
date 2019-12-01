package com.mallcai.biz.inventory.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.biz.inventory.dao.mapper.StoreWareHouseMapper;
import com.mallcai.biz.inventory.model.ErpStoreWarehouseDO;
import com.mallcai.biz.inventory.model.StoreWarehouseDO;
import com.mallcai.biz.inventory.model.WarehouseDO;
import com.mallcai.domain.inventory.dto.WarehouseDTO;
import com.mallcai.domain.inventory.request.StoreWarehouseRequest;
import com.mallcai.mq.producer.WarehouseStoreProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-01 16:36
 */
@Repository
@Slf4j
public class StoreWarehouseDAO {

    @Autowired
    private StoreWareHouseMapper storeWareHouseMapper;

    @Autowired
    private WarehouseStoreProducer warehouseStoreProducer;

    public List<StoreWarehouseDO> getWareHouseByStoreIds(List<Integer> storeIds) {
        return storeWareHouseMapper.getWareHouseByStoreIds(storeIds);
    }


    /**
     * 城市下有对应的仓 不分类型
     *
     * @param cityId
     * @return
     */
    public List<StoreWarehouseDO> getWareHouseByCityId(Integer cityId) {
        return storeWareHouseMapper.getWareHouseByCityId(cityId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchBindWarehouse(StoreWarehouseRequest request) {

        if (request.getWarehouses() == null || request.getWarehouses().isEmpty()) {
            return;
        }
        List<StoreWarehouseDO> list = request.getWarehouses().stream().map(warehouse -> {

            StoreWarehouseDO swdo = new StoreWarehouseDO();
            swdo.setStoreId(request.getStoreId());
            swdo.setWarehouseId(warehouse.getWarehouseId());
            swdo.setCreateUserId(request.getCreateUserId());
            return swdo;
        }).collect(Collectors.toList());


        storeWareHouseMapper.batchBindWarehouse(request.getStoreId(), list);

        List<StoreWarehouseDO> sts = getWareHouseByStoreIds(Lists.newArrayList(request.getStoreId()));
        Boolean result = warehouseStoreProducer.send(createMsg(sts));

        if (!result) {
            throw new RuntimeException("sync erp wstore error.");
        }

    }

    private JSONObject createMsg(List<StoreWarehouseDO> sts){
        List<ErpStoreWarehouseDO> erpDos = sts.stream().map(e -> {
            ErpStoreWarehouseDO erpDO = new ErpStoreWarehouseDO();
            BeanUtils.copyProperties(e, erpDO);
            return erpDO;
        }).collect(Collectors.toList());

        JSONObject res=new JSONObject();
        res.put("data",new JSONObject(){{
            put("storeId",erpDos.get(0).getStoreId());
            put("warehouseStores",erpDos);
        }});
        res.put("eventType","INSERT");
        res.put("primaryKey",-1);
        res.put("tableName","erp_warehouse_store");
        return res;

    }

    public void insertWarehouse(WarehouseDO warehouseDO) {
        storeWareHouseMapper.insertWarehouse(warehouseDO);
    }

    public void updateWarehouse(WarehouseDO warehouseDO) {
        storeWareHouseMapper.updateWarehouse(warehouseDO);
    }
}
