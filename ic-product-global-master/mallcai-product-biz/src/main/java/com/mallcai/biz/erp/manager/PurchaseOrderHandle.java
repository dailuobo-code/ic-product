package com.mallcai.biz.erp.manager;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.biz.erp.dao.PurchaseOrderDAO;
import com.mallcai.biz.erp.enums.ErpSyncOperateEnum;
import com.mallcai.biz.erp.enums.ErpSyncTableEnum;
import com.mallcai.biz.erp.model.ErpPurchaseOrderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-05 18:23
 */
@Service("purchaseOrderHandle")
@Slf4j
public class PurchaseOrderHandle extends AbstractErpSynHandle{


    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;
    @Override
    public Boolean match(String tableName) {
        return ErpSyncTableEnum.PURCHASE_UPDATE.getCode().equalsIgnoreCase(tableName);
    }

    @Override
    protected void doHandle(JSONObject message) {

        String data=message.getString("data");
        ErpPurchaseOrderDO order = JSONObject.parseObject(data, ErpPurchaseOrderDO.class);
        String eventType=message.getString("eventType");
        ErpSyncOperateEnum operateEnum = ErpSyncOperateEnum.getByCode(eventType);
        switch (operateEnum) {
            case INSERT:
                purchaseOrderDAO.addPurchaseOrder(order);
                log.info("addPurchaseOrder success . data:{}",data);
                break;
            case UPDATE:
                purchaseOrderDAO.deletePurchaseOrder(order);
                log.info("purchase_order status 5 changed to 4[状态逆流] just delete it. order_id:{}",order.getId());
                break;
            default:
                log.error("unsupport operation for erp_purchase_order. {}",eventType);
                return;
        }
    }
}
