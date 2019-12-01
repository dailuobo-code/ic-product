package com.mallcai.biz.product.converter;

import com.mallcai.biz.product.model.ServiceFeeTemplateDO;
import com.mallcai.domain.product.dto.ServiceFeeTemplateDTO;
import com.mallcai.domain.product.request.ModifyServiceFeeTemplateRequest;
import org.mapstruct.Mapper;

/**
 * ServiceFeeTemplateConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 16:37<br/>
 */
@Mapper(componentModel = "spring")
public interface ServiceFeeTemplateConverter {
    ServiceFeeTemplateDO dto2domain(ServiceFeeTemplateDTO s);

    ServiceFeeTemplateDTO domain2dto(ServiceFeeTemplateDO s);

    ServiceFeeTemplateDTO request2dto(ModifyServiceFeeTemplateRequest request);
}
