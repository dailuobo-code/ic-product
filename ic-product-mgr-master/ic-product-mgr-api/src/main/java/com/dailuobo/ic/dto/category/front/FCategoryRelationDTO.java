package com.dailuobo.ic.dto.category.front;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 前台类目关联后台类目数据DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FCategoryRelationDTO implements Serializable {

    private static final long serialVersionUID = -3233455286601176689L;


    /**
     * 关联Id
     */
    private BigInteger relationId;
    /**
     * 前台二级类目Id
     */
    private BigInteger frontCategoryId;

    /**
     * 关联前台类目一级-二级名称
     */
    private String relatedFCategoryName;
    /**
     * 关联的后台类型 1.后台类目，2。城市商品
     */
    private Integer relationType;

    /**
     * 关联类型展示名称
     */
    private String relationTypeShowName;

    /**
     * 影响范围，1。所有门店，2。指定门店 3。指定门店组
     */
    private Integer effectScopeType;


    private String effectScopeTypeShowName;

    /**
     * 影响的范围列表
     */
    private List<Integer> effectEntityIdList;

    /**
     * 创建人Id
     */
    private Integer createUserId;
    /**
     * 创建人名称
     */
    private Integer createUserName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改人Id
     */
    private Integer updateUserId;
    /**
     * 修改人名称
     */
    private String updateUserName;

    /**
     * 修改时间
     */
    private String updateTime;
}
