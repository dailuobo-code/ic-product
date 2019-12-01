package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.SignValue;
import org.apache.ibatis.annotations.Param;

/**
 * ...
 *
 * @author lihf@mallcai.com <lihaifeng>
 * @date 2019-05-16 13:48
 * @since Jdk 1.8
 */
public interface MysqlProductSignMapper {

    /**
     * 商品一分购可用标签
     *
     * @param cityId
     * @param storeId
     * @param cityProductId
     * @return
     */
    SignValue getSignValue(@Param("cityId") Integer cityId, @Param("storeId") Integer storeId, @Param("cityProductId") Integer cityProductId);
}
