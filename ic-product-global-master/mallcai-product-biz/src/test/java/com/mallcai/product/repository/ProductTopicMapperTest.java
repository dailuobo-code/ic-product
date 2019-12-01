package com.mallcai.product.repository;

import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.backend.search.common.utils.JsonMapper;
import com.mallcai.biz.product.dao.mapper.ProductTopicMapper;
import com.mallcai.biz.product.model.ProductTopicDO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mallcai.domain.enums.CommonStatus.DISABLE;
import static com.mallcai.domain.enums.CommonStatus.ENABLE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ProductTopicMapperTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 16:31<br/>
 */
public class ProductTopicMapperTest extends BaseTransactionalTest {

    @Autowired
    private ProductTopicMapper productTopicMapper;

    private ProductTopicDO productTopic;

    @Before
    public void setup() {
        productTopic = new ProductTopicDO();
        productTopic.setName("topic name");
        productTopic.setClassifyIds(Lists.newArrayList(1, 2, 3));
        productTopic.setOrder(1);
        productTopic.setOperatorId(1111);
        productTopic.setOperator("test-admin");
        productTopic.setStatus(ENABLE.name());
        productTopicMapper.create(productTopic);
        assertThat(productTopic.getId()).isNotNull();
    }

    @After
    public void tearDown() {
        productTopicMapper.delete(productTopic.getId());
    }

    @Test
    public void testBasic() {
        ProductTopicDO found = productTopicMapper.findById(productTopic.getId());
        assertThat(found).isNotNull();
        System.out.println(JsonMapper.nonEmptyMapper().toJson(found));

        ProductTopicDO update = new ProductTopicDO();
        update.setId(productTopic.getId());
        update.setOperatorId(1111);
        update.setClassifyIds(Lists.newArrayList(4, 5));
        update.setOperator("test-admin");
        update.setStatus(DISABLE.name());
        productTopicMapper.update(update);
        found = productTopicMapper.findById(productTopic.getId());
        assertThat(found).isNotNull();
        assertThat(found.getClassifyIds()).isEqualTo(update.getClassifyIds());
        System.out.println(JsonMapper.nonEmptyMapper().toJson(found));
    }
}
