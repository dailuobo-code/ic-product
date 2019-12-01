package com.mallcai.itemcenter.item.component;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import com.google.common.collect.Maps;
import com.mallcai.itemcenter.sku.repository.SkuDAO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ItemAccompanySkuIndex
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/23 16:58<br/>
 */
@Component
public class ItemAccompanySkuIndex {

    private static final String ITEM_ID_CACHE_NAME = "IndexOfItemId:";

    private static final String SKU_ID_CACHE_NAME = "IndexOfSkuId:";

    private static final int CACHE_TIME_OUT = 3600;

    private final SkuDAO skuDao;

    private static final String ITEM_ID_CACHE_KEY = "#skuId";

    private static final String SKU_ID_CACHE_KEY = "#itemId";

    public ItemAccompanySkuIndex(SkuDAO skuDao) {
        this.skuDao = skuDao;
    }

    @Cached(name = ITEM_ID_CACHE_NAME, key = ITEM_ID_CACHE_KEY, expire = CACHE_TIME_OUT)
    private Long findItemIdCore(Long skuId) {
        return skuDao.findItemId(skuId);
    }

    @Cached(name = SKU_ID_CACHE_NAME, key = SKU_ID_CACHE_KEY, expire = CACHE_TIME_OUT)
    private List<Long> findSkuIdCore(Long itemId) {
        return skuDao.findSkuId(itemId);
    }

    @CacheInvalidate(name = SKU_ID_CACHE_NAME, key = SKU_ID_CACHE_KEY)
    private void releaseSkuIdCacheCore(Long itemId) {
    }

    /**
     * 清除item和sku相关的索引缓存key（这应该在商品发生更新时调用，其它情况皆可忽略）
     * <p>
     * 当需要发生缓存清除时，可忽略sku，任其自然超时失效即可。保证item映射下的skuId正确
     * 便能够保证数据的正确性。
     *
     * @param itemId itemId
     */
    public void releaseCache(Long itemId) {
        releaseSkuIdCacheCore(itemId);
    }

    public Map<Long, Long> findItemId(Set<Long> skuIdSet) {
        Map<Long, Long> resultMap = Maps.newHashMapWithExpectedSize(skuIdSet.size());

        //Todo 循环读缓存
        for (Long skuId : skuIdSet) {
            Long itemId = findItemIdCore(skuId);

            if (itemId != null) {
                resultMap.put(skuId, itemId);
            }
        }

        return resultMap;
    }

    public Long findItemId(Long skuId) {
        return findItemIdCore(skuId);
    }

    public Map<Long, List<Long>> findSkuId(Set<Long> itemIdSet) {
        Map<Long, List<Long>> resultMap = Maps.newHashMapWithExpectedSize(itemIdSet.size());

        for (Long itemId : itemIdSet) {
            List<Long> skuIdList = findSkuIdCore(itemId);

            if (!CollectionUtils.isEmpty(skuIdList)) {
                resultMap.put(itemId, skuIdList);
            }
        }

        return resultMap;
    }

    public List<Long> findSkuId(Long itemId) {
        return findSkuIdCore(itemId);
    }

    public Long findFirstSkuId(Long itemId) {
        List<Long> skuIdList = findSkuIdCore(itemId);
        return CollectionUtils.isEmpty(skuIdList) ? null : skuIdList.iterator().next();
    }
}
