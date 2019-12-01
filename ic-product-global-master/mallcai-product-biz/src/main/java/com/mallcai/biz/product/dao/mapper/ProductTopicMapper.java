package com.mallcai.biz.product.dao.mapper;

import com.mallcai.biz.product.model.ProductTopicDO;

import java.util.List;
import java.util.Map;

/**
 * ProductTopicMapper
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-22 11:40<br/>
 */
public interface ProductTopicMapper {
    void create(ProductTopicDO productTopic);

    void delete(Integer id);

    ProductTopicDO findById(Integer id);

    void update(ProductTopicDO update);

    ProductTopicDO findByName(String name);

    List<ProductTopicDO> list(Map<String, Object> criteria);

    Long count(Map<String, Object> criteria);

    List<ProductTopicDO> paging(Map<String, Object> criteria);
}
