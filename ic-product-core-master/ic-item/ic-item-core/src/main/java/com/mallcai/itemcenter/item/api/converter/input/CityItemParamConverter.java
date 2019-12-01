package com.mallcai.itemcenter.item.api.converter.input;

import com.mallcai.itemcenter.common.BaseConverter;
import com.mallcai.itemcenter.item.api.bean.request.item.param.CityItemParam;
import com.mallcai.itemcenter.item.model.CityItem;
import com.mallcai.itemcenter.item.model.Item;
import org.mapstruct.Mapper;

/**
 * CityItemApiConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:05<br/>
 */
@Mapper(componentModel = "spring")
public interface CityItemParamConverter extends BaseConverter {
    CityItem dto2domain(CityItemParam s);

    default CityItem item2city(Item s) {
        if (s == null) {
            return null;
        }

        CityItem cityItem = new CityItem();

        cityItem.setItemId(s.getId());
        cityItem.setCategoryId(s.getCategoryId());
        cityItem.setSellerId(s.getSellerId());
        cityItem.setSellerName(s.getSellerName());
        cityItem.setName(s.getName());

        return cityItem;
    }
}
