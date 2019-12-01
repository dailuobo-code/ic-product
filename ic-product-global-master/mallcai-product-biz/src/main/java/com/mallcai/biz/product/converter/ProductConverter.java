package com.mallcai.biz.product.converter;

import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.open.api.model.product.ProductBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * ProductConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/27 14:51<br/>
 */
@Mapper(componentModel = "spring")
public interface ProductConverter {
    /**
     * TODO: pickClassifyName, category
     */
    @Mapping(target = "productRate", defaultValue = "0")
    @Mapping(source = "productId", target = "hqProductId")
    @Mapping(source = "isStandard", target = "standardFlag")
    @Mapping(source = "productPics", target = "productPicList")
    @Mapping(source = "categoryDTO.fatherId", target = "firstClassifyId")
    @Mapping(source = "categoryDTO.fatherName", target = "firstClassifyName")
    @Mapping(source = "categoryDTO.classifyId", target = "secondClassifyId")
    @Mapping(source = "categoryDTO.classifyName", target = "secondClassifyName")
    ProductBaseDTO dto2review(ProductDTO s);
}
