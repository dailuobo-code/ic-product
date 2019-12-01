package com.mallcai.itemcenter.item.repository;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import com.mallcai.itemcenter.common.Constant;
import com.mallcai.itemcenter.item.model.ItemDetail;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.springframework.stereotype.Repository;

/**
 * ItemDetailDAO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:00<br/>
 */
@Repository
public class ItemDetailDAO extends MyBatisDAO<ItemDetail> {
    private static final String CACHE_NAME = "ItemDetail:";

    /**
     * 根据商品id查询商品详情
     *
     * @param itemId   商品编号
     * @return 商品详情
     */
    @Cached(name = CACHE_NAME, key = "#itemId", expire = Constant.EXPIRE_15M)
    public ItemDetail findByItemIdCached(Long itemId) {
        return findByItemId(itemId);
    }

    public ItemDetail findByItemId(Long itemId) {
        return getSqlSession().selectOne(sqlId("findByItemId"), itemId);
    }

    @CacheInvalidate(name = CACHE_NAME, key = "#itemDetail.itemId")
    public Boolean updateByItemId(ItemDetail itemDetail) {
        return this.sqlSession.update(this.sqlId("updateByItemId"), itemDetail) == 1;
    }

    @CacheInvalidate(name = CACHE_NAME, key = "#itemId")
    public void invalid(Long itemId) { }
}
