package com.mallcai.biz.product;

import com.google.common.base.Throwables;
import com.mallcai.api.product.front.FrontProductService;
import com.mallcai.backend.common.redis.DefaultMasterJedisProxy;
import com.mallcai.biz.cacher.ProductServiceFeeCacheManager;
import com.mallcai.biz.cacher.ServiceFeeTemplateCacheManager;
import com.mallcai.biz.product.model.ProductServiceFeeDO;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductServiceFeeDTO;
import com.mallcai.domain.product.dto.ServiceFeeTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: FrontProductServiceImpl
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/8/27 下午5:28
 * @Version: 1.0
 **/
@Service("frontProductService")
@Slf4j
public class FrontProductServiceImpl implements FrontProductService {

    private final ProductServiceFeeCacheManager productServiceFeeCacheManager;
    private final ServiceFeeTemplateCacheManager serviceFeeTemplateCacheManager;

    public FrontProductServiceImpl(ProductServiceFeeCacheManager productServiceFeeCacheManager,
                                   ServiceFeeTemplateCacheManager serviceFeeTemplateCacheManager) {
        this.productServiceFeeCacheManager = productServiceFeeCacheManager;
        this.serviceFeeTemplateCacheManager = serviceFeeTemplateCacheManager;
    }

    public Long delGlobalRedisString(String key) {
        return DefaultMasterJedisProxy.getInstance().delKey(key);
    }

    public Long delGlobalRedisMultiString(List<String> keys) {
        return DefaultMasterJedisProxy.getInstance().delMultiKey(keys.toArray(new String[]{}));
    }

    public void setGlobalRedisString(String key, String value) {
        DefaultMasterJedisProxy.getInstance().set(key, value);
    }

    public void setGlobalRedisMultiString(Map<String, String> keyValueMap) {
        DefaultMasterJedisProxy.getInstance().setMultiKey(keyValueMap);
    }

    public void setGlobalRedisHash(String redisKey, String hashKey, String hashValue) {
        DefaultMasterJedisProxy.getInstance().setHashField(redisKey, hashKey, hashValue);
    }

    public Long setGlobalSortedSet(String redisKey, Map<String, Double> scoreMap) {
        return DefaultMasterJedisProxy.getInstance().setSortedSetMultiMember(redisKey, scoreMap);
    }

    @Override
    public ICResponse<List<ServiceFeeTemplateDTO>> queryServiceFeeByIds(Set<Long> ids) {
        try {
            List<ServiceFeeTemplateDTO> found = ids.stream()
                    .map(serviceFeeTemplateCacheManager::findById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            return ICResponse.success(found);
        } catch (Exception e) {
            log.error("fail to queryServiceFeeByIds by {}, cause:{}", ids, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("查找服务费模板失败");
        }
    }

    @Override
    public ICResponse<Map<Integer, ProductServiceFeeDTO>> queryServiceFeeByProductIds(Set<Integer> ids) {
        try {
            // 获取商品服务费绑定关系
            if (CollectionUtils.isEmpty(ids)) {
                return ICResponse.success(Collections.emptyMap());
            }
            Map<Integer, ProductServiceFeeDTO> found = productServiceFeeCacheManager.findByProductIds(ids);
            // 关联服务费模板到绑定关系
            Set<Long> templateIds = found.values().stream()
                    .filter(Objects::nonNull)
                    .map(ProductServiceFeeDTO::getTemplateId)
                    .collect(Collectors.toSet());
            Map<Long, ServiceFeeTemplateDTO> idToTemplate = templateIds.stream()
                    .map(serviceFeeTemplateCacheManager::findById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(ServiceFeeTemplateDTO::getId, Function.identity()));
            found.values().stream()
                    .filter(Objects::nonNull)
                    .forEach(it -> it.setServiceFeeTemplate(idToTemplate.get(it.getTemplateId())));
            return ICResponse.success(found);
        } catch (Exception e) {
            log.error("fail to queryServiceFeeByIds by {}, cause:{}", ids, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("查找商品服务费失败");
        }
    }

    @Override
    public ICResponse<Void> createProductFeeRelation(ProductServiceFeeDTO dto) {
        ProductServiceFeeDO productServiceFeeDO = new ProductServiceFeeDO();
        productServiceFeeDO.setCityId(dto.getCityId());
        productServiceFeeDO.setCityProductId(dto.getCityProductId());
        productServiceFeeDO.setTemplateId(dto.getTemplateId());
        productServiceFeeDO.setOperator("test");
        productServiceFeeDO.setOperatorId(0);
        productServiceFeeCacheManager.add(productServiceFeeDO);
        return ICResponse.success();
    }
}
