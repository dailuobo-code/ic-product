package com.dailuobo.ic.api.request;

import com.dailuobo.api.enums.ForecastFlagEnum;
import com.dailuobo.ic.api.base.ICBaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 商品预测属性变更请求参数
 */
@Getter
@Setter
@ToString()
public class ProductForecastFlagChangeRequest extends ICBaseRequest {
    /**
     * 城市Id
     */
    private Integer cityId;
    /**
     * 预测状态
     */
    private ForecastFlagEnum forecastFlagEnum;

    /**
     * 操作用户Id
     */
    private Integer operatorId;

    /**
     * 城市商品Id
     */
    private List<Integer> cityProductIdList;


    @Override
    public boolean checkParams() {
        if (CollectionUtils.isEmpty(cityProductIdList) || this.cityId == null || this.forecastFlagEnum == null || operatorId == null) {
            throw new IllegalArgumentException("参数异常");
        }
        return true;
    }
}
