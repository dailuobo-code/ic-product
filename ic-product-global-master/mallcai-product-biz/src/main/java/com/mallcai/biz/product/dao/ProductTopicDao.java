package com.mallcai.biz.product.dao;

import com.mallcai.backend.common.api.Paging;
import com.mallcai.biz.product.dao.mapper.ProductTopicMapper;
import com.mallcai.biz.product.model.ProductTopicDO;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;

/**
 * ProductTopicDao
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 21:21<br/>
 */
@Repository
public class ProductTopicDao {
    private final ProductTopicMapper mapper;

    public ProductTopicDao(ProductTopicMapper productTopicMapper) {
        this.mapper = productTopicMapper;
    }

    public Paging<ProductTopicDO> paging(Map<String, Object> criteria) {
        if (criteria == null) {
            criteria = Collections.emptyMap();
        }
        Long count = mapper.count(criteria);
        if (count == 0) {
            return Paging.empty();
        }
        return new Paging<>(count, mapper.paging(criteria));
    }
}
