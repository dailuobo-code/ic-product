package com.mallcai.biz.product.converter;

import com.mallcai.biz.product.model.ProductTopicDO;
import com.mallcai.domain.product.dto.ProductTopicDTO;
import com.mallcai.domain.product.request.CreateProductTopicRequest;
import com.mallcai.domain.product.request.UpdateOneProductTopicRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * ProductTopicConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 19:34<br/>
 */
@Mapper(componentModel = "spring")
public interface ProductTopicConverter {
    @Mapping(target = "classifies", ignore = true)
    ProductTopicDTO domain2dto(ProductTopicDO s);

    @Mapping(target = "classifies", ignore = true)
    ProductTopicDO dto2domain(ProductTopicDTO s);

    @Mappings({
            @Mapping(target = "classifies", ignore = true),
            @Mapping(source = "classifies", target = "classifyIds")})
    ProductTopicDO request2domain(CreateProductTopicRequest r);

    @Mappings({
            @Mapping(target = "classifies", ignore = true),
            @Mapping(source = "classifies", target = "classifyIds")})
    ProductTopicDO request2domain(UpdateOneProductTopicRequest r);
}
