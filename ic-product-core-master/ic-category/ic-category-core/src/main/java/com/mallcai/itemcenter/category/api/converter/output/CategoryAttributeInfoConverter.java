package com.mallcai.itemcenter.category.api.converter.output;

import com.mallcai.itemcenter.category.api.bean.response.CategoryAttributeInfo;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.common.BaseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * AttributeConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 16:51<br/>
 */
@Mapper(componentModel = "spring")
public interface CategoryAttributeInfoConverter extends BaseConverter {
    @Mappings({
            @Mapping(source = "attrMetasJson", target = "attrMetas"),
            @Mapping(source = "attrValsJson", target = "attrVals"),
    })
    CategoryAttributeInfo domain2dto(CategoryAttribute s);
}
