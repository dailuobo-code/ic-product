package com.mallcai.itemcenter.item.api.converter.output;

import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import com.mallcai.itemcenter.sku.model.Sku;
import org.mapstruct.Mapper;

/**
 * SkuInfoConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 22:24<br/>
 */
@Mapper
public interface SkuInfoConverter {
    SkuInfo domain2dto(Sku sku);
}
