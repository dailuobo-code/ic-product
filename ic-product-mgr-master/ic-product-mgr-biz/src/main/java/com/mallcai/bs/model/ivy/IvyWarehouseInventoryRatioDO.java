package com.mallcai.bs.model.ivy;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 表 ivy_warehouse_inventory_ratio
 * @author tianjunwei
 * @date 2019-09-17
 */
@Data
public class IvyWarehouseInventoryRatioDO implements Serializable {

    private Integer id;

    /**
     * 城市id
     */
    private Integer cityId;


    /**
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 告警比率
     */
    private Double warehouseWarningRatio;

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
