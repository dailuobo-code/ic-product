package com.dailuobo.ic.api.request;

import com.dailuobo.api.enums.ForecastFlagEnum;
import com.dailuobo.ic.api.base.CityPagedBaseRequest;
import lombok.Data;

import java.util.List;

/**
 * @author wangshifeng
 * @date 2019-09-04 15:46
 */
@Data
public class GetCityProductListRequest extends CityPagedBaseRequest {
    Integer classifyId;

    Integer parentClassifyId;

    String productNo;

    String hqProductName;

    String showName;

    Integer currentStatus;

    Integer[] cityProductIds;

    Byte deliveryMode;

    String isShare;

    String cityStatus;

    String isUndSearch;

    String isNewSearch;

    Integer groupSearch;

    Integer goodsTypeSearch;

    String isPointSearch;

    List<Integer> merchantIdList;

    ForecastFlagEnum flagEnum;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 售卖季节性
     */
    private Integer seasonal;

    /**
     * 货品是否关联
     */
    private Integer isGoodsRel;
}
