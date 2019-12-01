package com.mallcai.itemcenter.category.api.converter.input;

import com.mallcai.itemcenter.category.api.bean.request.param.CategoryAttributeParam;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.common.BaseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * CategoryAttributeParamConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/27 16:51<br/>
 */
@Mapper(componentModel = "spring")
public interface CategoryAttributeParamConverter extends BaseConverter {
    @Mappings({
            @Mapping(source = "attrMetas", target = "attrMetasJson"),
            @Mapping(source = "attrVals", target = "attrValsJson"),
            @Mapping(source = "extra", target = "extraJson"),
    })
    CategoryAttribute dto2domain(CategoryAttributeParam s);
}
