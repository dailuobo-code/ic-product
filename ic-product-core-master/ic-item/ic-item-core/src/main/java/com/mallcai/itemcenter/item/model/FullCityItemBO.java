package com.mallcai.itemcenter.item.model;

import com.mallcai.itemcenter.sku.model.CitySku;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FullCityItemBO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:23<br/>
 */
@Data
@NoArgsConstructor
public class FullCityItemBO {
    private CityItem cityItem;

    private List<CitySku> citySkus;

    public FullCityItemBO(CityItem cityItem, List<CitySku> citySkus) {
        this.cityItem = cityItem;
        this.citySkus = citySkus;
    }
}
