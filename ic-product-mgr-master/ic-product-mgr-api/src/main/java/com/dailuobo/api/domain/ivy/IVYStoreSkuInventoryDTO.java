package com.dailuobo.api.domain.ivy;


import lombok.Data;

import java.io.Serializable;


/**
 * 仓库存信息
 * @author tianjunwei
 * @date 2019-09-18
 */
@Data
public class IVYStoreSkuInventoryDTO implements Serializable {

    private Integer id;

    /**
     * 商品id
     */
    private Integer cityProductId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 门店名称
     */
    private Integer storeId;

    /**
     * 仓id
     */
    private Integer warehouseId;

    /**
     * 营销总库存
     */
    private Integer marketingNum;

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


}
