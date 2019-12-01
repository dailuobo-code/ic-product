package com.mallcai.itemcenter.item.api.converter.input;

import com.mallcai.itemcenter.common.BaseConverter;
import com.mallcai.itemcenter.item.api.bean.request.item.param.CitySkuParam;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.sku.model.CitySku;
import org.mapstruct.Mapper;

/**
 * CitySkuParamConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:08<br/>
 */
@Mapper
public interface CitySkuParamConverter extends BaseConverter {
    default CitySku sku2Bind(Sku s) {
        if (s == null) {
            return null;
        }

        CitySku citySku = new CitySku();

        citySku.setSkuId(s.getId());
        citySku.setBarcode(s.getBarcode());
        citySku.setItemId(s.getItemId());
        citySku.setSellerId(s.getSellerId());
        citySku.setPrice(s.getPrice());
        citySku.setPriceJson(s.getPriceJson());

        return citySku;
    }

    CitySku dto2domain(CitySkuParam s);
}
