package com.mallcai.itemcenter.category.api.facade;

import com.mallcai.itemcenter.category.api.bean.request.AttributeQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.CategoryAttributeInfo;
import com.mallcai.itemcenter.category.api.converter.output.CategoryAttributeInfoConverter;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.category.service.AttributeBaseDomainService;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.utils.GenericConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AttributeBaseReadFacadeImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 16:40<br/>
 */
@Slf4j
@Component
public class AttributeBaseReadFacadeImpl implements AttributeBaseReadFacade {
    private final CategoryAttributeInfoConverter categoryAttributeInfoConverter;
    private final AttributeBaseDomainService attributeBaseDomainService;

    public AttributeBaseReadFacadeImpl(CategoryAttributeInfoConverter categoryAttributeInfoConverter,
                                       AttributeBaseDomainService attributeBaseDomainService) {
        this.categoryAttributeInfoConverter = categoryAttributeInfoConverter;
        this.attributeBaseDomainService = attributeBaseDomainService;
    }

    /**
     * 根据后台类目id查询挂在该类目下的属性列表(包括从父类目继承的属性,去重复)
     */
    @Override
    public ICResponse<List<CategoryAttributeInfo>> findAllByCategoryId(AttributeQueryRequest request) {
        try {
            List<CategoryAttribute> attributes = attributeBaseDomainService.findAllByCategoryId(request.getCategoryId());
            return ICResponse.ok(GenericConverter.batchConvert(attributes, categoryAttributeInfoConverter::domain2dto));
        } catch (ServiceException e) {
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ICResponse.fail("attributes.find.fail");
        }
    }
}
