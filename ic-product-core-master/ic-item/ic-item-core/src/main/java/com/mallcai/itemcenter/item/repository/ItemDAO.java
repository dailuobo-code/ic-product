package com.mallcai.itemcenter.item.repository;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import com.mallcai.itemcenter.common.Constant;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * ItemDAO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:00<br/>
 */
@Repository
public class ItemDAO extends MyBatisDAO<Item> {
    private static final String CACHE_NAME = "Item:";
    public static final String DEFAULT_KEY = "#id";

    /**
     * 通过id查询
     *
     * @param id 商品id
     * @return 商品
     */
    @Cached(name = CACHE_NAME, key = DEFAULT_KEY, expire = Constant.EXPIRE_15M)
    public Item findByIdCached(Long id) {
        return findById(id);
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

    @CacheInvalidate(name = CACHE_NAME, key = DEFAULT_KEY)
    public void invalid(Long id) { }
}
