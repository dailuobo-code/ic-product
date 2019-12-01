package com.mallcai.domain.category.front.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 类目关联配置对象
 */
@Data
public class FrontCategoryAssociatedConfigurationDTO implements Serializable {
    private static final long serialVersionUID = 8398165784498861890L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 前台类目
     */
    private Long frontCategoryId;

    /**
     * 前台类目名称
     */
    private String frontCategoryName;

    /**
     * 父类目ID
     */
    private Long frontParentCategoryId;

    /**
     * 父类目名称
     */
    private String frontParentCategoryName;

    /**
     * '关联类型 1.后台类目，2.商品'
     */
    private Integer associatedType;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 最后更新时间
     */
    private String lastUpdateTime;

    /**
     * 最后更新人
     */
    private Integer lastOperatorId;
}
