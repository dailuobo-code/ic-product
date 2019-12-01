package com.mallcai.biz.inventory.model;

import lombok.Data;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-05 18:14
 */
@Data
public class ErpStoreWarehouseDO {

    private Integer id;
    private Integer warehouseId;
    private Integer storeId;
    private Integer createUserId;
}
