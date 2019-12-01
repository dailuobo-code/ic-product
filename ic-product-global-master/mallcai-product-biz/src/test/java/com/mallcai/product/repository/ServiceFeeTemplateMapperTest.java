package com.mallcai.product.mapper;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.backend.search.common.utils.JsonMapper;
import com.mallcai.biz.product.converter.ServiceFeeTemplateConverter;
import com.mallcai.biz.product.dao.mapper.ServiceFeeTemplateMapper;
import com.mallcai.biz.product.model.ServiceFeeTemplateDO;
import com.mallcai.domain.product.dto.ServiceFeeTemplateDTO;
import com.mallcai.domain.enums.ChargeMethod;
import com.mallcai.domain.enums.CommonStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ServiceFeeTemplateMapper
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 17:31<br/>
 */
public class ServiceFeeTemplateMapperTest extends BaseTransactionalTest {
    @Autowired
    private ServiceFeeTemplateMapper serviceFeeTemplateMapper;
    @Autowired
    private ServiceFeeTemplateConverter serviceFeeTemplateConverter;

    private ServiceFeeTemplateDO serviceFeeTemplate;
    private List<Long> cleanup;

    @Before
    public void setup() {
        ServiceFeeTemplateDTO dto = make();
        serviceFeeTemplate = serviceFeeTemplateConverter.dto2domain(dto);
        serviceFeeTemplateMapper.create(serviceFeeTemplate);
        assertThat(serviceFeeTemplate.getId()).isNotNull();
        cleanup = Lists.newArrayList(serviceFeeTemplate.getId());
    }

    @After
    public void tearDown() {
        for (Long id : cleanup) {
            serviceFeeTemplateMapper.delete(id);
        }
    }

    public ServiceFeeTemplateDTO make() {
        ServiceFeeTemplateDTO dto = new ServiceFeeTemplateDTO();
        dto.setCityId(30);
        dto.setName("default template");
        dto.setIsDefault(true);
        dto.setStatus(CommonStatus.ENABLE);
        dto.setChargeMethod(ChargeMethod.AMOUNT);
        dto.setInitAmount(1);
        dto.setInitFee(BigDecimal.valueOf(0.2));
        dto.setIncrAmount(1);
        dto.setIncrFee(BigDecimal.valueOf(0.2));
        dto.setOperator("test-admin");
        dto.setOperatorId(1111);
        return dto;
    }

    @Test
    public void testBasic() {
        ServiceFeeTemplateDO found = serviceFeeTemplateMapper.findById(serviceFeeTemplate.getId());
        System.out.println(JsonMapper.nonEmptyMapper().toJson(found));
        assertThat(found).isNotNull();
    }

    @Test
    public void testFindEnabled() {
        ServiceFeeTemplateDO another = serviceFeeTemplateConverter.dto2domain(make());
        another.setIsDefault(Boolean.FALSE);
        serviceFeeTemplateMapper.create(another);

        List<ServiceFeeTemplateDO> enable = serviceFeeTemplateMapper.findByCityId(serviceFeeTemplate.getCityId());
        assertThat(enable).isNotEmpty().hasSize(2);

        cleanup.add(another.getId());
    }

    @Test
    public void testFindDefault() {
        serviceFeeTemplateMapper.notDefault(serviceFeeTemplate.getCityId());

        ServiceFeeTemplateDO another = serviceFeeTemplateConverter.dto2domain(make());
        another.setIsDefault(Boolean.FALSE);
        serviceFeeTemplateMapper.create(another);

        serviceFeeTemplateMapper.setDefault(ImmutableMap.of("id", another.getId(), "cityId", another.getCityId()));
        ServiceFeeTemplateDO defFee = serviceFeeTemplateMapper.findDefaultByCityId(serviceFeeTemplate.getCityId());
        assertThat(defFee.getId()).isEqualTo(another.getId());

        cleanup.add(another.getId());
    }
}
