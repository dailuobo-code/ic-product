package com.dailuobo.api.domain.ivy;


import lombok.Data;

import java.io.Serializable;

/**
 * 商品实物库存实体
 * @author tianjunwei
 * @date  2019-09-18
 */
@Data
public class IVYMarketingInventoryServiceDTO implements Serializable {

    private Integer id;

    /**
     * 规格id
     */
    private Integer specId;

    /**
     * 商品城市id
     */
    private Integer cityProductId;

    /**
     * 可用量
     */
    private Integer available;

    /**
     * 门店id
     */
    private Integer storeId;

    /**
     * 仓id
     */
    private Integer warehouseId;

    /**
     * 安全库存
     */
    private Integer threshold;

    /**
     * 营销活动库存量
     */
    private Integer activityAvailable;


}
