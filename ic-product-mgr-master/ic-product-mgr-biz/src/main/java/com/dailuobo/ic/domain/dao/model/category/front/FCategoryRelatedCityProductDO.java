package com.dailuobo.ic.domain.dao.model.category.front;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FCategoryRelatedCityProductDO {
    private BigInteger id;
    /**
     * 关联关系Id
     */
    private BigInteger relationId;

    /**
     * 城市Id
     */
    private Integer cityId;
    /**
     * 城市商品Id
     */
    private Integer cityProductId;

    /**
     * sku
     */
    private String productNo;
    /**
     * 展示序列
     */
    private Integer displayOrder;

    /**
     * 创建人Id
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer updateUserId;

    /**
     * 修改时间
     */
    private Integer updateTime;

    /**
     * 删除标示
     */
    private Integer isDeleted;
}
