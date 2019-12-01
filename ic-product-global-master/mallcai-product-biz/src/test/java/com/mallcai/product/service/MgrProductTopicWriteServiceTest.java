package com.mallcai.product.service;

import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.MgrProductTopicWriteService;
import com.mallcai.backend.search.common.utils.JsonMapper;
import com.mallcai.biz.cacher.ProductTopicCacheManager;
import com.mallcai.biz.product.dao.mapper.ProductTopicMapper;
import com.mallcai.biz.product.model.ProductTopicDO;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductTopicDTO;
import com.mallcai.domain.product.request.CreateProductTopicRequest;
import com.mallcai.domain.product.request.DeleteOneProductTopicRequest;
import com.mallcai.domain.product.request.UpdateOneProductTopicRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mallcai.domain.enums.CommonStatus.ENABLE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * MgrProductTopicWriteSerivceTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 20:11<br/>
 */
public class MgrProductTopicWriteServiceTest extends BaseTransactionalTest {
    @Autowired
    private MgrProductTopicWriteService mgrProductTopicWriteService;
    @Autowired
    private ProductTopicMapper productTopicMapper;
    @Autowired
    private ProductTopicCacheManager productTopicCacheManager;

    private Integer id;
    private CreateProductTopicRequest create;

    @Before
    public void setup() {
        create = CreateProductTopicRequest.builder().build();
        create.setName("topic name");
        create.setClassifies(Lists.newArrayList(15, 18, 21));
        create.setOrder(1);
        create.setOperatorId(1111);
        create.setOperator("test-admin");
        create.setStatus(ENABLE);
        ICResponse<Integer> createR = mgrProductTopicWriteService.createProductTopic(create);
        assertThat(createR.isSuccess()).isTrue();
        assertThat(createR.getData()).isNotNull();
        id = createR.getData();
    }

    @After
    public void tearDown() {
        productTopicMapper.delete(id);
    }

    @Test
    public void testUpdate() {
        UpdateOneProductTopicRequest update = UpdateOneProductTopicRequest.builder()
                .id(id)
                .operator("admin-test")
                .operatorId(2222)
                .name("topic name b")
                .status(ENABLE)
                .classifyNos(Lists.newArrayList("112", "113", "114"))
                .order(2)
                .build();
        ICResponse<Boolean> updateR = mgrProductTopicWriteService.updateOneProductTopic(update);
        assertThat(updateR.isSuccess()).isTrue();

        ProductTopicDO found = productTopicMapper.findById(id);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(found));
        assertThat(found.getName()).isEqualTo(update.getName());
        assertThat(found.getClassifyIds()).containsExactly(18, 21, 24);
        assertThat(found.getOrder()).isEqualTo(2);

        ProductTopicDTO dto = productTopicCacheManager.findById(id);
        assertThat(dto.getName()).isEqualTo(update.getName());
        assertThat(dto.getClassifyIds()).containsExactly(18, 21, 24);
        assertThat(dto.getOrder()).isEqualTo(2);

        dto = productTopicCacheManager.listActive().get(0);
        assertThat(dto.getName()).isEqualTo(update.getName());
        assertThat(dto.getClassifyIds()).containsExactly(18, 21, 24);
        assertThat(dto.getOrder()).isEqualTo(2);
    }

    @Test
    public void testUpdateCategoryIds() {
        UpdateOneProductTopicRequest update = UpdateOneProductTopicRequest.builder()
                .id(id)
                .operator("admin-test")
                .operatorId(2222)
                .name("topic name b")
                .order(2)
                .build();
        ICResponse<Boolean> updateR = mgrProductTopicWriteService.updateOneProductTopic(update);
        assertThat(updateR.isSuccess()).isTrue();

        ProductTopicDO found = productTopicMapper.findById(id);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(found));
        assertThat(found.getName()).isEqualTo(update.getName());
        assertThat(found.getClassifyIds()).containsExactlyInAnyOrder(15, 18, 21);
        assertThat(found.getOrder()).isEqualTo(2);
    }

    @Test
    public void testDelete() {
        DeleteOneProductTopicRequest delete = DeleteOneProductTopicRequest.builder()
                .id(id)
                .operatorId(3333)
                .operator("test-admin")
                .build();
        ICResponse<Boolean> deleteR = mgrProductTopicWriteService.deleteOneProductTopic(delete);
        assertThat(deleteR.isSuccess()).isTrue();

        assertThat(productTopicMapper.findById(id)).isNull();
        assertThat(productTopicCacheManager.findById(id)).isNull();
        assertThat(productTopicCacheManager.listActive()).isEmpty();
    }
}
