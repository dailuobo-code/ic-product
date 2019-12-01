package com.mallcai.bs.model.ivy;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: zhangtengpo
 * @Date: 2019-09-23 20:23
 * @description:
 */
@Data
@Builder
public class IvyMarketingStockPageQueryDO {

    /**
     * 城市商品id
     */
    private List<Integer> cityProductIdList;

    /**
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 门店id
     */
    private Integer storeId;

    /**
     * 城市ID
     */
    private Integer cityId;

    private Integer limit;

    private Integer offset;
}
