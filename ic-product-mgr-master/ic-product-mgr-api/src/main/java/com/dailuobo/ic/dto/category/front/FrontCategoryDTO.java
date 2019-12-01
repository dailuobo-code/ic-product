package com.dailuobo.ic.dto.category.front;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 前台分类对象
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FrontCategoryDTO implements Serializable {
    private static final long serialVersionUID = -7967137973262635545L;

    /**
     * 前台分类Id
     */
    private Integer categoryId;
    /**
     * 前台分类名称
     */
    private Integer categoryName;
    /**
     * 展示序列
     */
    private Integer showOrder;
    /**
     * 分类级别
     */
    private Integer level;
    /**
     * 子分类id
     */
    private List<FrontCategoryDTO> itemList;
}
