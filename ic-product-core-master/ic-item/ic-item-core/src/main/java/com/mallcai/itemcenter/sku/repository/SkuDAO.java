package com.mallcai.itemcenter.sku.repository;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import com.google.common.collect.ImmutableMap;
import com.mallcai.itemcenter.common.Constant;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SkuDAO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:00<br/>
 */
@Repository
public class SkuDAO extends MyBatisDAO<Sku> {
    private static final String CACHE_NAME = "Sku:";
    public static final String DEFAULT_KEY = "#id";
    private static final String SKU_ID_CACHE_NAME = "ItemIdIndexSkuId:";

    /**
     * 查找sku
     *
     * @param id skuId
     * @return sku对象
     * @checked
     */
    @Cached(name = CACHE_NAME, key = DEFAULT_KEY, expire = Constant.EXPIRE_15M)
    public Sku findById(Long id) {
        return getSqlSession().selectOne(sqlId("findById"), id);
    }

    /**
     * 通过商品id获取skuId集合
     *
     * @param itemId 商品id
     * @return
     */
    @Cached(name = SKU_ID_CACHE_NAME, key = "#itemId", expire = Constant.EXPIRE_15M)
    public List<Long> findSkuIdByItemId(Long itemId) {
        return getSqlSession().selectList(sqlId("findSkuIdByItemId"), itemId);
    }

    @CacheInvalidate(name = SKU_ID_CACHE_NAME, key = "#itemId")
    public void releaseItemIdIndex(Long itemId) {
        // Just for release.
    }

    @CacheInvalidate(name = CACHE_NAME, key = DEFAULT_KEY)
    public boolean updateStatus(Long id, Integer version, Integer status, Long updatedBy) {
        return sqlSession.update(sqlId("updateStatus"), ImmutableMap.of(
                "id", id,
                "version", version,
                "status", status,
                "updatedBy", updatedBy)
        ) == 1;
    }

    /**
     * 根据商品id查找对应的sku
     *
     * @param itemId 商品id
     * @return 对应的sku列表
     * @checked
     */
    public List<Sku> findByItemId(Long itemId) {
        return getSqlSession().selectList(sqlId("findByItemId"), itemId);
    }

    public List<Long> findSkuId(Long itemId) {
        return sqlSession.selectList(sqlId("findSkuId"), itemId);
    }

    public Long findItemId(Long skuId) {
        return sqlSession.selectOne(sqlId("findItemId"), skuId);
    }

    @CacheInvalidate(name = CACHE_NAME, key = DEFAULT_KEY)
    public Boolean updateStatusCheckSellerId(Long id, Integer version, Integer status, Long sellerId, Long updatedBy) {
        Map<String, Object> criteriaMap = new HashMap<>();
        criteriaMap.put("id", id);
        criteriaMap.put("version", version);
        criteriaMap.put("status", status);
        criteriaMap.put("sellerId", sellerId);
        criteriaMap.put("updatedBy", updatedBy);

        return sqlSession.update(sqlId("updateStatusCheckSellerId"), criteriaMap) == 1;
    }
}
