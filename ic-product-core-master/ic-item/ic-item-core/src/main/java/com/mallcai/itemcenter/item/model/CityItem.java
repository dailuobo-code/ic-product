package com.mallcai.itemcenter.item.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:36<br/>
 */
@Data
public class CityItem implements Serializable {

    private static final long serialVersionUID = 358480129469700694L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 城市id
     */
    private Long cityId;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 店铺id
     */
    private Long sellerId;

    /**
     * 店铺名
     */
    private String sellerName;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品状态, 1: 上架, -1:下架, -2:冻结, -3:删除
     */
    private Integer status;

    /**
     * 信息版本号
     */
    private Integer version;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private Long updatedBy;

    /**
     * 创建者
     */
    private Long createdBy;
}
