package com.mallcai.product.service;

import com.google.common.collect.Sets;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.front.FrontProductService;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductServiceFeeDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FrontProductServiceImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/3 20:12<br/>
 */
public class FrontProductServiceTest extends BaseTransactionalTest {
    @Autowired
    private FrontProductService frontProductService;

    @Test
    public void testFindProductFee() {
        ICResponse<Map<Integer, ProductServiceFeeDTO>> r = frontProductService.queryServiceFeeByProductIds(Collections.emptySet());
        System.out.println(r);
        assertThat(r.isSuccess()).isTrue();

        r = frontProductService.queryServiceFeeByProductIds(Sets.newHashSet(2403, 1163, 1166));
        System.out.println(r);
        assertThat(r.isSuccess()).isTrue();

        r = frontProductService.queryServiceFeeByProductIds(Sets.newHashSet(1, 2, 3));
        System.out.println(r);
        assertThat(r.isSuccess()).isTrue();
    }
}
