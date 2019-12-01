package com.mallcai.api.product.front;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductServiceFeeDTO;
import com.mallcai.domain.product.dto.ServiceFeeTemplateDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: FrontProductService
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/8/27 下午5:27
 * @Version: 1.0
 **/
public interface FrontProductService {

    Long delGlobalRedisString(String key);

    Long delGlobalRedisMultiString(List<String> keys);

    void setGlobalRedisString(String key, String value);

    void setGlobalRedisMultiString(Map<String, String> keyValueMap);

    void setGlobalRedisHash(String redisKey, String hashKey, String hashValue);

    Long setGlobalSortedSet(String redisKey, Map<String, Double> scoreMap);

    ICResponse<List<ServiceFeeTemplateDTO>> queryServiceFeeByIds(Set<Long> ids);

    ICResponse<Map<Integer, ProductServiceFeeDTO>> queryServiceFeeByProductIds(Set<Integer> ids);


    ICResponse<Void> createProductFeeRelation(ProductServiceFeeDTO dto );
}
