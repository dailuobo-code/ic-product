package com.mallcai.domain.category.front.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 类目关联的条目对象
 */
@Data
public class FrontCategoryAssociatedItemRequest implements Serializable {
    private static final long serialVersionUID = 8398165784498861890L;

    /**
     * 类目ID或商品的productNo,productno改为hqproductid
     */
    private String id;

    /**
     * 排序
     */
    private Integer sort;

    private String productNo;

}
