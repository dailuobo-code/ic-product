package com.dailuobo.api.domain.ivy;


import com.dailuobo.api.common.PageDTO;
import java.util.List;
import lombok.Data;

/**
 * 门店实物库存查询
 * @author tianjunwei
 * @date 2019-09-19
 */
@Data
public class IVYMarketingStockQueryDTO extends PageDTO {

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

}
