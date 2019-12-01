package com.mallcai.biz.cacher;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.mallcai.biz.product.dao.mapper.ProductServiceFeeMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ServiceFeeTemplateLocalCacheManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/28 16:45<br/>
 */
@Component
public class ProductServiceFeeLocalCacheManager {
    private final ProductServiceFeeMapper productServiceFeeMapper;

    public ProductServiceFeeLocalCacheManager(ProductServiceFeeMapper productServiceFeeMapper) {
        this.productServiceFeeMapper = productServiceFeeMapper;
    }

    /**
     * 根据 ID 查找一个服务费，TODO: 后续增加批量获取接口
     * @return
     */
    @CachePenetrationProtect
    @Cached(name = "ProductServiceFeeLocalCacheManager.findAllProductIds",
            key = "'all'",
            cacheNullValue = true,
            cacheType = CacheType.LOCAL,
            localExpire = 30,
            localLimit = 1,
            timeUnit = TimeUnit.MINUTES)
    public Set<Integer> findAllProductIds() {
        return productServiceFeeMapper.findAllProductIds();
    }
}
