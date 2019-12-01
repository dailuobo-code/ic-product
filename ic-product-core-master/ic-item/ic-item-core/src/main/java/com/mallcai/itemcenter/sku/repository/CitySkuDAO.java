package com.mallcai.itemcenter.sku.repository;

import com.google.common.collect.ImmutableMap;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CitySkuDao
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:36<br/>
 */
@Repository
public class CitySkuDAO extends MyBatisDAO<CitySku> {
    // todo: should cache here
    public List<CitySku> findByItemIdAndCityId(Long itemId, Long cityId) {
        return getSqlSession().selectList(
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
