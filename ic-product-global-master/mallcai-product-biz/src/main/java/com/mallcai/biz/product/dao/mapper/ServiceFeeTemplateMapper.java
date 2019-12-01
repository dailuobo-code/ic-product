package com.mallcai.biz.product.dao.mapper;

import com.mallcai.biz.product.model.ServiceFeeTemplateDO;

import java.util.List;
import java.util.Map;

/**
 * ServiceFeeTemplateMapper
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 16:13<br/>
 */
public interface ServiceFeeTemplateMapper {
    void create(ServiceFeeTemplateDO create);

    void delete(Long id);

    void update(ServiceFeeTemplateDO update);

    ServiceFeeTemplateDO findById(Long id);

    List<ServiceFeeTemplateDO> findByIds(List<Long> ids);

    ServiceFeeTemplateDO findDefaultByCityId(Integer cityId);

    ServiceFeeTemplateDO findByCityIdAndName(ServiceFeeTemplateDO criteria);

    List<ServiceFeeTemplateDO> findByCityId(Integer cityId);

    Long count(Map<String, Object> criteria);

    List<ServiceFeeTemplateDO> paging(Map<String, Object> criteria);

    void notDefault(Integer cityId);

    void updateStatus(ServiceFeeTemplateDO criteria);

    void setDefault(Map<String, Object> criteria);
}
