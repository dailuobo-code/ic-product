package com.mallcai.biz.cacher;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.mallcai.biz.product.converter.ServiceFeeTemplateConverter;
import com.mallcai.biz.product.dao.mapper.ServiceFeeTemplateMapper;
import com.mallcai.biz.product.model.ServiceFeeTemplateDO;
import com.mallcai.domain.product.dto.ServiceFeeTemplateDTO;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ServiceFeeTemplateCacheManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/3 11:28<br/>
 */
@Component
public class ServiceFeeTemplateCacheManager {
    private final ServiceFeeTemplateMapper serviceFeeTemplateMapper;
    private final ServiceFeeTemplateConverter serviceFeeTemplateConverter;

    public ServiceFeeTemplateCacheManager(ServiceFeeTemplateMapper serviceFeeTemplateMapper,
                                          ServiceFeeTemplateConverter serviceFeeTemplateConverter) {
        this.serviceFeeTemplateMapper = serviceFeeTemplateMapper;
        this.serviceFeeTemplateConverter = serviceFeeTemplateConverter;
    }

    /**
     * 根据 ID 查找一个服务费，TODO: 后续增加批量获取接口
     */
    @CachePenetrationProtect
    @Cached(name = "ServiceFeeTemplate.findById", cacheType = CacheType.BOTH, localExpire = 30, expire = 60, timeUnit = TimeUnit.MINUTES)
    public ServiceFeeTemplateDTO findById(Long id) {
        ServiceFeeTemplateDO found = serviceFeeTemplateMapper.findById(id);
        return serviceFeeTemplateConverter.domain2dto(found);
    }
}
