package com.mallcai.itemcenter.item.api.converter.input;

import com.mallcai.itemcenter.common.BaseConverter;
import com.mallcai.itemcenter.item.api.bean.request.item.param.CitySkuParam;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.sku.model.Sku;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * CitySkuParamConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:08<br/>
 */
@Component
public class LogicCitySkuParamConverter extends CitySkuParamConverterImpl implements BaseConverter {


    public CitySku dto2domain(CitySkuParam s) {
        CitySku sku = super.dto2domain(s);

        Map<String, String> extra = sku.getExtra() != null ? sku.getExtra() : new HashMap<>();
        if (s.getSalesType() != null) {
            extra.put("salesType", s.getSalesType().name());
        }
        if (s.getPackageWeight() != null) {
            extra.put("packageWeight", s.getPackageWeight().toString());
        }
        if (s.getPackageQuantity() != null) {
            extra.put("packageQuantity", s.getPackageQuantity().toString());
        }
        sku.setExtra(extra);

        Boolean isEnable = s.getEnable();
        if (isEnable == null || isEnable) {
            sku.setStatus(ItemStatus.ON_SHELF.getValue());
        } else {
            sku.setStatus(ItemStatus.OFF_SHELF.getValue());
        }
        return sku;
    }
}
