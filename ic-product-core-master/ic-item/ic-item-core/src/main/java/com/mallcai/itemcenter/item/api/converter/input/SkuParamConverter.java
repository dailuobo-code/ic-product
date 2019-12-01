package com.mallcai.itemcenter.item.api.converter.input;

import com.mallcai.itemcenter.common.BaseConverter;
import com.mallcai.itemcenter.item.api.bean.request.item.param.SkuParam;
import com.mallcai.itemcenter.sku.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * SkuApiConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:44<br/>
 */
@Mapper
public interface SkuParamConverter extends BaseConverter {
    @Mapping(source = "extraPrice", target = "priceJson")
    Sku dto2domain(SkuParam sku);
}
