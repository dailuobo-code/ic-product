package com.mallcai.biz.product.manager;

import com.mallcai.biz.product.converter.ServiceFeeTemplateConverter;
import com.mallcai.biz.product.dao.mapper.ServiceFeeTemplateMapper;
import com.mallcai.biz.product.model.ServiceFeeTemplateDO;
import com.mallcai.domain.product.dto.ServiceFeeTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ServiceFeeDomainService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/6 00:29<br/>
 */
@Slf4j
@Component
public class ServiceFeeDomainService {
    private final ServiceFeeTemplateMapper serviceFeeTemplateMapper;
    private final ServiceFeeTemplateConverter serviceFeeTemplateConverter;

    public ServiceFeeDomainService(ServiceFeeTemplateMapper serviceFeeTemplateMapper,
                                   ServiceFeeTemplateConverter serviceFeeTemplateConverter) {
        this.serviceFeeTemplateMapper = serviceFeeTemplateMapper;
        this.serviceFeeTemplateConverter = serviceFeeTemplateConverter;
    }

    public Boolean updateServiceFeeTemplate(ServiceFeeTemplateDTO criteria) {
        ServiceFeeTemplateDO found = serviceFeeTemplateMapper.findById(criteria.getId());
        if (found == null) {
            log.error("failed to find ServiceFeeTemplateDO by {}", criteria);
            return false;
        }

        ServiceFeeTemplateDO update = serviceFeeTemplateConverter.dto2domain(criteria);
        update.setId(found.getId());
        serviceFeeTemplateMapper.update(update);
        return Boolean.TRUE;
    }
}
