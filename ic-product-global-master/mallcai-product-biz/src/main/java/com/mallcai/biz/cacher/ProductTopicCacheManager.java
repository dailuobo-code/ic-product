package com.mallcai.biz.cacher;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.google.common.collect.ImmutableMap;
import com.mallcai.backend.search.common.utils.GenericConverter;
import com.mallcai.biz.product.converter.ProductTopicConverter;
import com.mallcai.biz.product.dao.mapper.ProductTopicMapper;
import com.mallcai.biz.product.model.ProductTopicDO;
import com.mallcai.domain.product.dto.ProductTopicDTO;
import com.mallcai.domain.enums.CommonStatus;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductTopicCacheManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 19:26<br/>
 */
@Component
public class ProductTopicCacheManager {

    private final ProductTopicMapper productTopicMapper;
    private final ProductTopicConverter productTopicConverter;

    public ProductTopicCacheManager(ProductTopicMapper productTopicMapper, ProductTopicConverter productTopicConverter) {
        this.productTopicMapper = productTopicMapper;
        this.productTopicConverter = productTopicConverter;
    }

    @CachePenetrationProtect
    @Cached(name = "ProductTopicCacheManager.listActive", cacheType = CacheType.BOTH)
    public List<ProductTopicDTO> listActive() {
        List<ProductTopicDO> found = productTopicMapper.list(
                ImmutableMap.of("status", CommonStatus.ENABLE.name()));

        List<ProductTopicDO> sorted = found.stream()
                .sorted(Comparator.comparingInt(ProductTopicDO::getOrder).thenComparing(ProductTopicDO::getCreateTime))
                .collect(Collectors.toList());

        return GenericConverter.batchConvert(sorted, productTopicConverter::domain2dto);
    }

    @CachePenetrationProtect
    @Cached(name = "ProductTopicCacheManager.findById", cacheType = CacheType.BOTH)
    public ProductTopicDTO findById(Integer id) {
        ProductTopicDO found = productTopicMapper.findById(id);
        return productTopicConverter.domain2dto(found);
    }

    @CacheInvalidate(name = "ProductTopicCacheManager.listActive")
    public void expireActive() { }

    @CacheInvalidate(name = "ProductTopicCacheManager.findById")
    public void expireById(Integer id) { }
}
