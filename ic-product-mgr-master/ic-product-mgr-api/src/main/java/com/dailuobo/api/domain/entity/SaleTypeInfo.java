package com.dailuobo.api.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Sjn
 * @Date 2019-08-04 12:53
 * @Description 商品售卖方式相关信息
 */
@Data
public class SaleTypeInfo implements Serializable {

    private static final long serialVersionUID = -6178525417438838117L;

    /**城市商品id*/
    private Integer cityProductId;
    /**门店id*/
    private Integer storeId;
    /**售卖方式  按重量（找零）-1  按数量（不找零）-2*/
    private Integer changeFlag;
    /**打包上限*/
    private Integer packageMaxWeight;
    /**每份数量*/
    private Integer packageQuantity;
    /**更新人*/
    private Integer updateUserId;

}
