package com.mallcai.product.mapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.biz.cacher.ProductServiceFeeCacheManager;
import com.mallcai.biz.product.dao.mapper.ProductServiceFeeMapper;
import com.mallcai.biz.product.model.ProductServiceFeeDO;
import com.mallcai.domain.product.dto.ProductServiceFeeDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ProductServiceFeeMapperTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/3 12:41<br/>
 */
public class ProductServiceFeeMapperTest extends BaseTransactionalTest {
    @Autowired
    private ProductServiceFeeMapper productServiceFeeMapper;
    @Autowired
    private ProductServiceFeeCacheManager productServiceFeeCacheManager;

    private List<Long> clean = Lists.newArrayList();
    private ProductServiceFeeDO productServiceFee;

    @Before
    public void setup() {
        productServiceFee = new ProductServiceFeeDO();
        productServiceFee.setCityId(30);
        productServiceFee.setCityProductId(1);
        productServiceFee.setTemplateId(Long.MAX_VALUE);
        productServiceFee.setOperator("test-admin");
        productServiceFee.setOperatorId(1111);
        productServiceFeeMapper.create(productServiceFee);
        assertThat(productServiceFee.getId()).isNotNull();

        clean = Lists.newArrayList(productServiceFee.getId());
    }

    @After
    public void tearDown() {
        for (Long id : clean) {
            productServiceFeeMapper.delete(id);
        }
    }

    @Test
    public void testFindByCityProduct() {
        List<ProductServiceFeeDO> found =
                productServiceFeeMapper.findByProductIds(Collections.singletonList(1));
        assertThat(found).isNotEmpty().hasSize(1);

        Set<Integer> ids = productServiceFeeMapper.findAllProductIds();
        assertThat(ids).isNotEmpty();
    }

    @Test
    public void testLoadFromCache() {
        Map<Integer, ProductServiceFeeDTO> r = productServiceFeeCacheManager.findByProductIds(Sets.newHashSet(1, 2));
        assertThat(r).isNotEmpty().containsKeys(1, 2);
        assertThat(r.get(1)).isNotNull();
        assertThat(r.get(2)).isNull();
    }
}
