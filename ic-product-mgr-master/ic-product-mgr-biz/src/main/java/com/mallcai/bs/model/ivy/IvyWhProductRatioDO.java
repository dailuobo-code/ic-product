package com.mallcai.bs.model.ivy;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class IvyWhProductRatioDO implements Serializable {
    private Integer id;

    /**
     * 城市id
     */
    private Integer cityId;


    /**
     * 仓库库存比例id
     */
    private Integer warehouseInventoryRatioId;

    /**
     * 城市商品id
     */
    private Integer cityProductId;

    /**
     * 城市商品平均销量
     */
    private Integer avgSaleCount;

    /**
     * 城市商品销售占比
     */
    private Double cityProductRatio;


    /**
     * 创建者
     */
    private Integer creator;

    /**
     * 修改者
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
