package com.dailuobo.api.domain.ivy;

import java.io.Serializable;
import lombok.Data;

/**
 * 库存分配商品白名单
 * @author tianjunwei
 * @date 2019-09-18
 */

@Data
public class IVYWarehouseSkuWhiteDTO implements Serializable {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 仓名称
     */
    private String warehouseName;

    /**
     * 商品展示名称
     */
    private String showName;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 城市商品id
     */
    private Integer cityProductId;

    private Integer creator;

    private Integer updator;
}
