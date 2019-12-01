package com.mallcai.itemcenter.item.repository;

import com.google.common.collect.ImmutableMap;
import com.mallcai.itemcenter.item.model.CityItem;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CityItemDAO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:00<br/>
 */
@Repository
public class CityItemDAO extends MyBatisDAO<CityItem> {
    public List<CityItem> findByItemId(Long id) {
        return getSqlSession().selectList(sqlId("findByItemId"), id);
    }

    // todo: should cache here
    public CityItem findByItemIdAndCityId(Long itemId, Long cityId) {
        return getSqlSession().selectOne(
                sqlId("findByItemIdAndCityId"),
                ImmutableMap.of("cityId", cityId, "itemId", itemId));
    }

    // todo: should invalid cache here
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
