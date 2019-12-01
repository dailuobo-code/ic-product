package com.mallcai.product.service;

import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.MgrProductTopicReadService;
import com.mallcai.api.product.backend.MgrProductTopicWriteService;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.search.common.utils.JsonMapper;
import com.mallcai.biz.cacher.ProductTopicCacheManager;
import com.mallcai.biz.product.dao.mapper.ProductTopicMapper;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductTopicDTO;
import com.mallcai.domain.product.request.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.mallcai.domain.enums.CommonStatus.ENABLE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * MgrProductTopicReadServiceTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 21:33<br/>
 */
public class MgrProductTopicReadServiceTest extends BaseTransactionalTest {
    @Autowired
    private MgrProductTopicWriteService mgrProductTopicWriteService;
    @Autowired
    private MgrProductTopicReadService mgrProductTopicReadService;
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
        // create.setClassifies(Lists.newArrayList(427, 428, 429));
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
    public void testFind() {
        QueryOneProductTopicRequest findOne = new QueryOneProductTopicRequest(id);
        ICResponse<ProductTopicDTO> findOneR = mgrProductTopicReadService.queryOneProductTopic(findOne);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(findOneR.getData()));
        assertThat(findOneR.isSuccess()).isTrue();
        assertThat(findOneR.getData()).isNotNull();
        assertThat(findOneR.getData().getClassifies()).isNotEmpty();

        UpdateOneProductTopicRequest update = UpdateOneProductTopicRequest.builder()
                .id(id)
                .operator("admin-test")
                .operatorId(2222)
                .status(ENABLE)
                .build();
        mgrProductTopicWriteService.updateOneProductTopic(update);
        QueryActiveProductTopicRequest listActive = QueryActiveProductTopicRequest.instance();
        ICResponse<List<ProductTopicDTO>> listActiveR = mgrProductTopicReadService.queryActiveProductTopic(listActive);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(listActiveR.getData()));
        assertThat(listActiveR.isSuccess()).isTrue();
        assertThat(listActiveR.getData()).isNotNull();
        assertThat(listActiveR.getData().size()).isEqualTo(1);
    }

    @Test
    public void testPaging() {
        PagingProductTopicRequest page = PagingProductTopicRequest.builder()
                .name("topic")
                .build();
        ICResponse<Paging<ProductTopicDTO>> pageR = mgrProductTopicReadService.pagingProductTopic(page);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(pageR.getData()));
        assertThat(pageR.isSuccess()).isTrue();
        assertThat(pageR.getData()).isNotNull();
        assertThat(pageR.getData().getData().size()).isEqualTo(1);
    }

    @Test
    public void testListActive() {
        ICResponse<List<ProductTopicDTO>> listR = mgrProductTopicReadService.queryActiveProductTopic(QueryActiveProductTopicRequest.instance());
        assertThat(listR.isSuccess()).isTrue();
        assertThat(listR.getData()).isNotEmpty();
        System.out.println(JsonMapper.nonEmptyMapper().toJson(listR));
    }
}
