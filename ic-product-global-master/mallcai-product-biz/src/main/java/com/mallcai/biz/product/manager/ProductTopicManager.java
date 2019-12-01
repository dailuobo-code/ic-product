package com.mallcai.biz.product.manager;

import com.google.common.collect.ImmutableMap;
import com.mallcai.backend.common.exception.ServiceException;
import com.mallcai.biz.cacher.ProductTopicCacheManager;
import com.mallcai.biz.product.dao.mapper.ProductTopicMapper;
import com.mallcai.biz.product.model.ProductTopicDO;
import com.mallcai.domain.enums.CommonStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mallcai.domain.enums.CommonStatus.ENABLE;

/**
 * ProductTopicManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 17:34<br/>
 */
@Slf4j
@Component
public class ProductTopicManager {
    @Value("${product.recommend-topic.max-active:4}")
    private Integer maxActive;

    private final ProductTopicMapper productTopicMapper;
    private final ProductTopicCacheManager productTopicCacheManager;

    public ProductTopicManager(ProductTopicMapper productTopicMapper,
                               ProductTopicCacheManager productTopicCacheManager) {
        this.productTopicMapper = productTopicMapper;
        this.productTopicCacheManager = productTopicCacheManager;
    }

    public void create(ProductTopicDO topic) {
        if (productTopicMapper.findByName(topic.getName()) != null) {
            throw new ServiceException("当前分类名称已存在");
        }
        topic.setStatus(CommonStatus.DISABLE.name());
        if (!CollectionUtils.isEmpty(topic.getClassifyIds())) {
            topic.setClassifyIds(topic.getClassifyIds().stream().distinct().collect(Collectors.toList()));
        }
        productTopicMapper.create(topic);
    }

    public void update(ProductTopicDO topic) {
        if (StringUtils.isNotBlank(topic.getName()) && productTopicMapper.findByName(topic.getName()) != null) {
            throw new ServiceException("当前分类名称已存在");
        }
        if (Objects.equals(ENABLE.name(), topic.getStatus())) {
            List<ProductTopicDO> active = productTopicMapper.list(ImmutableMap.of("status", ENABLE.name()));
            if (active.size() >= maxActive) {
                log.error("启用失败，当前一个城市最多允4个首页分类，当前数量 {}", active.size());
                throw new ServiceException("启用失败，当前一个城市最多允4个首页分类");
            }
        }
        if (!CollectionUtils.isEmpty(topic.getClassifyIds())) {
            topic.setClassifyIds(topic.getClassifyIds().stream().distinct().collect(Collectors.toList()));
        }

        productTopicMapper.update(topic);
        if (Objects.equals(ENABLE.name(), topic.getStatus())) {
            productTopicCacheManager.expireActive();
        }
        productTopicCacheManager.expireById(topic.getId());
    }

    public void delete(ProductTopicDO topic) {
        ProductTopicDO delete = new ProductTopicDO();
        delete.setId(topic.getId());
        delete.setDeleted(Boolean.TRUE);
        productTopicMapper.update(delete);

        // 刷新缓存
        if (Objects.equals(ENABLE.name(), topic.getStatus())) {
            productTopicCacheManager.expireActive();
            productTopicCacheManager.listActive();
        }
        productTopicCacheManager.expireById(topic.getId());
    }

    private List<Integer> distinctClassify(List<Integer> ids) {
        return ids.stream().distinct().collect(Collectors.toList());
    }
}
