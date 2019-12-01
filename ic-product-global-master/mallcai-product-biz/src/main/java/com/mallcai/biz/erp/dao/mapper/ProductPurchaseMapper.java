package com.mallcai.biz.erp.dao.mapper;


import com.mallcai.biz.erp.model.ErpPurchaseOrderDO;
import com.mallcai.biz.erp.model.ErpPurchaseOrderDetailDO;
import com.mallcai.biz.erp.model.ProductPurchaseDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by mac on 19/7/4.
 */
public interface ProductPurchaseMapper  {



    /**
     * 选择性insert
     * 熟悉null igonre
     * @param erpPurchaseOrderDO
     */
    void insertOrder(ErpPurchaseOrderDO erpPurchaseOrderDO);

    /**
     * delete order and detail by id
     * @param id
     */
    Long deletePurchaseOrderAndDetail(@Param("id") Integer id);

    Long selectOrderCnt(@Param("id") Integer id);



    void batchInsertDetail(@Param("list") List<ErpPurchaseOrderDetailDO> list);

}
