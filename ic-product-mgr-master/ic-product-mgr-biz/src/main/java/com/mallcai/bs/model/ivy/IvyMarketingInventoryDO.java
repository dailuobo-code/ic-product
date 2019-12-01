package com.mallcai.bs.model.ivy;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class IvyMarketingInventoryDO implements Serializable {

    private Integer id;

    /**
     * 仓id
     */
    private Integer warehouseId;

    /**
     * 商品id
     */
    private Integer cityProductId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 仓名称
     */
    private String warehouseName;

    /**
     * 营销总库存
     */
    private Integer marketingNum;

    /**
     * 营销总库存
     */
    private Integer oldVersionMarketingNum;

    /**
     * 可售库存
     */
    private Integer availableNum;

    /**
     * 可售库存
     */
    private Integer oldVersionAvailableNum;

    /**
     * 锁定库存
     */
    private Integer lockNum;

    /**
     * 锁定库存
     */
    private Integer oldVersionLockNum;


    /**
     * 活动库存
     */
    private Integer activityNum;

    /**
     * 活动库存
     */
    private Integer oldVersionActivityNum;

    /**
     * 预售库存
     */
    private Integer presellNum;

    /**
     * 预售库存
     */
    private Integer oldVersionPresellNum;

    /**
     * 告警库存
     */
    private Integer alarmNum;

    /**
     * 告警库存
     */
    private Integer oldVersionAlarmNum;

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
