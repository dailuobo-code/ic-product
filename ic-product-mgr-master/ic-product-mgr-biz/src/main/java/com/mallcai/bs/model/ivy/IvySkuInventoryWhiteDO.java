package com.mallcai.bs.model.ivy;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 表 ivy_sku_inventory_white
 * @author tianjunwei
 * @date 2019-09-18
 */
@Data
public class IvySkuInventoryWhiteDO implements Serializable {

    private Integer id;

    /**
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 城市商品id
     */
    private Integer cityProductId;


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
