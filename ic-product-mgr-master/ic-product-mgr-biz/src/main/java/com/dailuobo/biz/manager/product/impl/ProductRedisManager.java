package com.dailuobo.biz.manager.product.impl;

import com.dailuobo.biz.manager.product.ProductCacheManager;
import com.dailuobo.biz.util.Constants;
import com.dailuobo.ic.api.util.CacheKeyGenerator;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.backend.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据缓存实现：Redis 实现
 * @author wangshifeng
 * @date 2019-08-08 21:03
 */
@Slf4j
@Component
public class ProductRedisManager implements ProductCacheManager {

    @Override
    public List<Integer> getTodayCityForecastProductIds(Integer cityId) {
        String key = CacheKeyGenerator.generateCityNotSharedKey(cityId, DateUtils.getToday());
        List<String> notShareMem = JedisProxy.getInstance().getSetMembersAsList(key);
        if (CollectionUtils.isEmpty(notShareMem)) {
            return Collections.emptyList();
        }
        return notShareMem.stream()
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public void addTodayCityForecastProductIds(Integer cityId, List<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return;
        }
        String key = CacheKeyGenerator.generateCityNotSharedKey(cityId, DateUtils.getToday());
        JedisProxy.getInstance().putSet(
                key,
                productIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())
        );

        JedisProxy.getInstance().setKeyExexpire(key, Constants.RedisConstants.CITY_FORECAST_KEY_TIME_OUT);

    }

    @Override
    public List<Integer> getTodayStoreForecastProductIds(Integer storeId) {
        String key = CacheKeyGenerator.generateNotSharedKey(storeId, DateUtils.getToday());
        List<String> notShareMem = JedisProxy.getInstance().getSetMembersAsList(key);
        if (CollectionUtils.isEmpty(notShareMem)) {
            return Collections.emptyList();
        }
        return notShareMem.stream()
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
