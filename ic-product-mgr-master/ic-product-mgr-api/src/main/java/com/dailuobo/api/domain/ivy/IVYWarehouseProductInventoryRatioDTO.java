package com.dailuobo.api.domain.ivy;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 仓库库存比例表 城市所在仓分配比例
 * @author tianjunwei
 * @date 2019-09-17
 */
@Data
public class IVYWarehouseProductInventoryRatioDTO implements Serializable {

    private Integer id;

    /**
     * 仓库城市比例id 必填
     */
    private Integer warehouseInventoryatioId;

    /**
     * 商品id
     */
    private Integer cityProductId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String hqProductName;

    /**
     * 展示名称
     */
    private String showName;


    /**
     * 商品销量
     */
    private Integer saleCount;

    /**
     * 商品销售占比
     */
    private Double cityProductRatio;

    /**
     * 城市 id
     */
    private Integer cityId;


    /**
     * 创建者 id 非必填
     */
    private Integer creator;

    /**
     * 更新着 id 非必填
     */
    private Integer updator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
