package com.mallcai.biz.product.converter;

import com.mallcai.biz.product.model.ProductServiceFeeDO;
import com.mallcai.domain.product.dto.ProductServiceFeeDTO;
import org.mapstruct.Mapper;

/**
 * ProductServiceFeeConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/3 12:59<br/>
 */
@Mapper(componentModel = "spring")
public interface ProductServiceFeeConverter {
    ProductServiceFeeDTO domain2dto(ProductServiceFeeDO s);
}
