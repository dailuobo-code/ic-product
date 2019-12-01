package com.dailuobo.biz.service.redis;

import com.mallcai.backend.common.redis.JedisProxy;
import org.springframework.stereotype.Service;

@Service
public class InventoryRedisService {
    private final static String REDIS_PREFIX = "ivy:";

    public void delStoreWareHouseProductAvailableRedisKey(Integer cityProductId, Integer storeId, Integer wareHouseId) {

        final String key = REDIS_PREFIX + ":city_product_id:" + cityProductId + ":store_id:" + storeId + ":warehouse_id:" + wareHouseId;

        JedisProxy.getInstance().delKey(key);

    }
}
