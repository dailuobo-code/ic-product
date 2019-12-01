package com.mallcai.biz.product.dao.mapper;

import com.mallcai.biz.product.model.ProductServiceFeeDO;

import java.util.List;
import java.util.Set;

/**
 * ServiceFeeTemplateMapper
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 16:13<br/>
 */
public interface ProductServiceFeeMapper {
    void create(ProductServiceFeeDO create);

    void delete(Long id);

    void update(ProductServiceFeeDO update);

    ProductServiceFeeDO findById(Long id);

    List<ProductServiceFeeDO> findByIds(List<Long> ids);

    ProductServiceFeeDO findByProductId(Integer id);

    List<ProductServiceFeeDO> findByProductIds(List<Integer> ids);

    Set<Integer> findAllProductIds();
}
