package com.mallcai.bs.model.ivy;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表 ivy_store_sku_inventory
 * @author tianjunwei
 * @date 2019-09-17
 */

@Data
public class IvyStoreSkuInventoryDO implements Serializable {

    private Integer id;

    /**
     * 门店id
     */
    private Integer storeId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 营销库存id
     */
    private Integer marketingInventoryId;

    /**
     * 城市商品 id
     */
    private Integer cityProductId;

    /**
     * 可售库存
     */
    private Integer availableNum;

    /**
     * 锁定库存
     */
    private Integer lockNum;

    /**
     * 创建者
     */
    private Integer creator;

    /**
     * 修改者
     */
    private Integer updator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
