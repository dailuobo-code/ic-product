package com.dailuobo.api.domain.ivy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Author: mengjia
 * @Date: 2019-07-09 14:11
 * @Version 1.0
 * storeId，cityProductId，warehouseId为唯一键
 */

@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IVYStockDTO implements Serializable {

    /**
     * 门店ID
     */
    private Integer storeId;

    /**
     * 城市商品Id
     */
    private Integer cityProductId;

    /**
     * 仓ID ，目前除合肥共享商品（标或非标外 仓默认为-1
     */
    private Integer warehouseId;

    /**
     * 可用
     */
    private Integer available;

    /**
     * 预警量
     */
    private Integer threshold;
}
