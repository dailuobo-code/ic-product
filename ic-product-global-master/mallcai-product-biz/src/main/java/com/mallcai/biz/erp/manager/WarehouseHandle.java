package com.mallcai.biz.erp.manager;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.biz.erp.dao.PurchaseOrderDAO;
import com.mallcai.biz.erp.enums.ErpSyncOperateEnum;
import com.mallcai.biz.erp.enums.ErpSyncTableEnum;
import com.mallcai.biz.erp.model.ErpPurchaseOrderDO;
import com.mallcai.biz.inventory.dao.StoreWarehouseDAO;
import com.mallcai.biz.inventory.model.WarehouseDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-05 18:23
 */
@Service("warehouseHandle")
@Slf4j
public class WarehouseHandle extends AbstractErpSynHandle{


    @Autowired
    private StoreWarehouseDAO storeWarehouseDAO;


    @Override
    public Boolean match(String tableName) {
        return ErpSyncTableEnum.WAREHOUSE.getCode().equalsIgnoreCase(tableName);
    }

    @Override
    protected void doHandle(JSONObject message) {

        String data=message.getString("data");
        WarehouseDO warehouseDO = JSONObject.parseObject(data, WarehouseDO.class);
        String eventType=message.getString("eventType");
        ErpSyncOperateEnum operateEnum = ErpSyncOperateEnum.getByCode(eventType);
        switch (operateEnum) {
            case INSERT:
                storeWarehouseDAO.insertWarehouse(warehouseDO);
                log.info("insertWarehouse success . data:{}",data);
                break;
            case UPDATE:
                storeWarehouseDAO.updateWarehouse(warehouseDO);
                log.info("updateWarehouse success . data:{}",data);
                break;
            default:
                log.error("unsupport operation for table erp_warehouse. {}",eventType);
                return;
        }
    }
}
