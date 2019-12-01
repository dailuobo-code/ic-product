package com.dailuobo.ic.api.request.product;

import com.dailuobo.ic.api.base.CityBaseRequest;
import com.dailuobo.ic.api.base.CityPagedBaseRequest;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductListQueryRequest extends CityPagedBaseRequest {

    /**
     * 商品编号
     */
    private String productNo;

    private String productName;


    /**
     * 城市商品Id列表
     */
    private List<Integer> cityProductIdList;


    @Override
    public boolean checkParams() {
        if (productNo == null) {
            throw new IllegalArgumentException("ProductNo 不可为空");
        }
        if (cityId == null || cityId < 0) {
            throw new IllegalArgumentException("cityId 不可为空");
        }
        if (CollectionUtils.isNotEmpty(cityProductIdList) && cityProductIdList.size() > 100) {
            throw new IllegalArgumentException("cityProductIdList 不可为超过100条");
        }
        return true;
    }
}
