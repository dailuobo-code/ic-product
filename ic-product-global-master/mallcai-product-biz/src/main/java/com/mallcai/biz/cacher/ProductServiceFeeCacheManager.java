package com.mallcai.biz.cacher;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheResult;
import com.alicp.jetcache.MultiGetResult;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.google.common.collect.Maps;
import com.mallcai.biz.product.converter.ProductServiceFeeConverter;
import com.mallcai.biz.product.dao.mapper.ProductServiceFeeMapper;
import com.mallcai.biz.product.model.ProductServiceFeeDO;
import com.mallcai.domain.product.dto.ProductServiceFeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ServiceFeeTemplateCacheManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/3 11:28<br/>
 */
@Slf4j
@Component
public class ProductServiceFeeCacheManager {
    private final ProductServiceFeeMapper productServiceFeeMapper;
    private final ProductServiceFeeConverter productServiceFeeConverter;

    private final ProductServiceFeeLocalCacheManager productServiceFeeLocalCacheManager;

    public ProductServiceFeeCacheManager(ProductServiceFeeMapper productServiceFeeMapper,
                                         ProductServiceFeeConverter productServiceFeeConverter,
                                         ProductServiceFeeLocalCacheManager productServiceFeeLocalCacheManager) {
        this.productServiceFeeMapper = productServiceFeeMapper;
        this.productServiceFeeConverter = productServiceFeeConverter;
        this.productServiceFeeLocalCacheManager = productServiceFeeLocalCacheManager;
    }

    @CreateCache(name = "", cacheType = CacheType.BOTH, localLimit = 1000, expire = 30, localExpire = 10, timeUnit = TimeUnit.MINUTES)
    private Cache<String, ProductServiceFeeDTO> productFeeCache;

    public Map<Integer, ProductServiceFeeDTO> findByProductIds(Set<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        // 去除无效查询
        Set<Integer> allIds = productServiceFeeLocalCacheManager.findAllProductIds();
        ids.retainAll(allIds);
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        // 获取缓存中关联关系数据
        Map<String, Integer> keys = ids.stream().collect(Collectors.toMap(this::key, Function.identity()));
        MultiGetResult<String, ProductServiceFeeDTO> getAllR = productFeeCache.GET_ALL(keys.keySet());
        if (!getAllR.isSuccess()) {
            log.error("failed to get product service fee by {}", ids);
            return Collections.emptyMap();
        }
        Map<String, ProductServiceFeeDTO> result = getAllR.unwrapValues();
        // 缓慢未命中部分从数据库加载
        ids.removeAll(result.keySet().stream().map(keys::get).collect(Collectors.toSet()));
        if (!ids.isEmpty()) {
            List<ProductServiceFeeDO> fees = productServiceFeeMapper.findByProductIds(new ArrayList<>(ids));
            Map<String, ProductServiceFeeDTO> found = Maps.newHashMap();
            // 未命中部分可能只有部分有关联关系
            // 没有从数据库加载的需要置为 null
            for (Integer id : ids) {
                ProductServiceFeeDO fee = fees.stream()
                        .filter(it -> Objects.equals(it.getCityProductId(), id))
                        .findAny()
                        .orElse(null);
                found.put(key(id), productServiceFeeConverter.domain2dto(fee));
            }
            result.putAll(found);
            CacheResult putR = productFeeCache.PUT_ALL(found);
            if (!putR.isSuccess()) {
                log.error("failed to put caches {}", found);
            }
        }

        Map<Integer, ProductServiceFeeDTO> r = Maps.newHashMap();
        result.forEach((key, value) -> r.put(keys.get(key), value));
        return r;
    }

    private String key(Integer cityProductId) {
        return "ProductServiceFeeCacheManager.findById:" + cityProductId;
    }

    public void add(ProductServiceFeeDO productServiceFeeDO){
        productServiceFeeMapper.create(productServiceFeeDO);
    }
}
