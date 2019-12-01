package com.mallcai.product.service;

import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.biz.cacher.ProductServiceFeeLocalCacheManager;
import com.mallcai.biz.product.dao.mapper.ServiceFeeTemplateMapper;
import com.mallcai.biz.product.model.ServiceFeeTemplateDO;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.request.ModifyServiceFeeTemplateRequest;
import com.mallcai.domain.enums.ChargeMethod;
import com.mallcai.domain.enums.CommonStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MgrProductServiceTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/6 00:34<br/>
 */
public class ServiceFeeServiceTest extends BaseTransactionalTest {
    @Autowired
    private MgrProductService mgrProductService;
    @Autowired
    private ServiceFeeTemplateMapper serviceFeeTemplateMapper;

    private ServiceFeeTemplateDO serviceFeeTemplateDO;

    @Before
    public void setup() {
        serviceFeeTemplateDO = new ServiceFeeTemplateDO();
        serviceFeeTemplateDO.setCityId(30);
        serviceFeeTemplateDO.setName("test-template");
        serviceFeeTemplateDO.setStatus(CommonStatus.ENABLE.name());
        serviceFeeTemplateDO.setChargeMethod(ChargeMethod.AMOUNT.name());
        serviceFeeTemplateDO.setIsDefault(true);
        serviceFeeTemplateDO.setInitAmount(1);
        serviceFeeTemplateDO.setInitFee(BigDecimal.valueOf(2));
        serviceFeeTemplateDO.setIncrAmount(1);
        serviceFeeTemplateDO.setIncrFee(BigDecimal.valueOf(2));
        serviceFeeTemplateDO.setOperatorId(1111);
        serviceFeeTemplateDO.setOperator("test");
        serviceFeeTemplateDO.setFee(BigDecimal.ZERO);
        serviceFeeTemplateMapper.create(serviceFeeTemplateDO);
        assertThat(serviceFeeTemplateDO.getId()).isNotNull();
    }

    @After
    public void tearDown() {
        serviceFeeTemplateMapper.delete(serviceFeeTemplateDO.getId());
    }

    @Autowired
    private ProductServiceFeeLocalCacheManager productServiceFeeLocalCacheManager;

    @Test
    public void testUpdate() {
        Set<Integer> ids = productServiceFeeLocalCacheManager.findAllProductIds();
        ids = productServiceFeeLocalCacheManager.findAllProductIds();

        ModifyServiceFeeTemplateRequest modify = new ModifyServiceFeeTemplateRequest();
        modify.setId(serviceFeeTemplateDO.getId());
        modify.setInitFee(BigDecimal.valueOf(20));
        modify.setIncrFee(BigDecimal.valueOf(20));
        ICResponse<Boolean> modifyR = mgrProductService.updateServiceFeeTemplate(modify);
        assertThat(modifyR.isSuccess()).isTrue();
        assertThat(modifyR.getData()).isTrue();

        ServiceFeeTemplateDO u = serviceFeeTemplateMapper.findById(serviceFeeTemplateDO.getId());
        assertThat(u.getInitFee()).isEqualByComparingTo(BigDecimal.valueOf(20));
        assertThat(u.getIncrFee()).isEqualByComparingTo(BigDecimal.valueOf(20));
    }
}
