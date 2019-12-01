package com.dailuobo.api.domain.ivy;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 仓库存信息
 * @author tianjunwei
 * @date 2019-09-18
 */
@Data
public class IVYMarketingWhInventoryDTO implements Serializable {


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
     * 剩余未分配实物总库存
     */
    private Integer remainderAvailable;

    /**
     * 可售库存
     */
    private Integer availableNum;

    /**
     * 锁定库存
     */
    private Integer lockNum;


    /**
     * 活动库存
     */
    private Integer activityNum;

    /**
     * 预售库存
     */
    private Integer presellNum;

    /**
     * 告警库存
     */
    private Integer alarmNum;

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
