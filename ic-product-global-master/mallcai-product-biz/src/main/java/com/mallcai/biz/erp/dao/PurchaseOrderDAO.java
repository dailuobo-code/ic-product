package com.mallcai.biz.erp.dao;

import com.mallcai.biz.erp.dao.mapper.ProductPurchaseMapper;
import com.mallcai.biz.erp.model.ErpPurchaseOrderDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-07-31 16:32
 */
@Repository
public class PurchaseOrderDAO {

    @Autowired
    private ProductPurchaseMapper productPurchaseMapper;

    @Transactional(rollbackFor = Exception.class)
    public void addPurchaseOrder(ErpPurchaseOrderDO order){

        if(productPurchaseMapper.selectOrderCnt(order.getId())>0){
            //如果存在历史记录先删除.....
            productPurchaseMapper.deletePurchaseOrderAndDetail(order.getId());
        }
        productPurchaseMapper.insertOrder(order);
        productPurchaseMapper.batchInsertDetail(order.getDetailList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseOrder(ErpPurchaseOrderDO order){
        productPurchaseMapper.deletePurchaseOrderAndDetail(order.getId());
    }
}
