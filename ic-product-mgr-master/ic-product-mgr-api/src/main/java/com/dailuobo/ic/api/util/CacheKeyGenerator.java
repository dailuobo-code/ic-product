package com.dailuobo.ic.api.util;


import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;

/**
 * 商品中心redis key生成器
 *
 * @Author: mengjia
 * @Date: 2019-05-23 20:32
 * @Version 1.0
 */
public class CacheKeyGenerator extends RedisKeyGenerator {

    /**
     * 商品中心redis前缀
     */
    private final static String REDIS_PREFIX = "ic:";

    public static String generateCityNotSharedKey(Integer cityId, String date) {
        return REDIS_PREFIX + "notShared:cityId:" + cityId + ":date:" + date;
    }

    /**
     * 城市商品属性
     * @param cityProductId
     * @return
     */
    public static String generateExtraAttr(Integer cityProductId) {
        return REDIS_PREFIX + "product_extra_attr:city_product:" + cityProductId;
    }

}
