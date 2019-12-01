package com.mallcai.domain.category.front.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 类目关联的条目对象
 */
@Data
public class FrontCategoryAssociatedItemDTO implements Serializable {
    private static final long serialVersionUID = 8398165784498861890L;

    /**
     * 类目ID或商品productNo
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 商品图片
     */
    private String productImage;

    /**
     * 商品类目名称
     */
    private String productCategoryName;

    /**
     * 商品编号
     */
    private String productNo;

}
