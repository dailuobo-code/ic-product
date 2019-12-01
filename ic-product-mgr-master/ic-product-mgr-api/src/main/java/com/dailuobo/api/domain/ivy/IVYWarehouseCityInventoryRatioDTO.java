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
public class IVYWarehouseCityInventoryRatioDTO implements Serializable {

    private Integer id;

    /**
     * 城市id 必填
     */
    private Integer cityId;

    /**
     * 城市名称 非必填
     */
    private String cityName;

    /**
     * 仓库id 必填
     */
    private Integer warehouseId;

    /**
     * 必填
     * 城市库存占比，总和不能超过 100，
     */
    private Double warehouseWarningRatio;

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
