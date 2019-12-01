package com.dailuobo.ic.domain.dao.model.category.front;

import com.google.common.collect.Lists;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 城市=》前台类目关联关系
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FrontCategoryRelationDO {
    private BigInteger id;
    /**
     * 前台类目Id：二级类目Id
     */
    private BigInteger frontCategoryId;

    /**
     * 城市Id
     */
    private Integer cityId;
    /**
     * 关联类型 1。后台类目，2。商品
     */
    private Integer relationType;
    /**
     * 影响范围 1。所有门店，2指定门店id，3。指定门店组Id
     */
    private Integer effectScope;
    /**
     * 影响的Id列表
     */
    private String effectScopeIds;
    /**
     * 删除标示 0正常 1删除
     */
    private Integer isDeleted;
    /**
     * 创建人id
     */
    private Integer createUserId;
    /**
     * 更新人id
     */
    private Integer updateUserId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;


    public List<Integer> getEffectScopeIdList() {
        if (StringUtils.isEmpty(effectScopeIds)) {
            return Lists.newArrayList();
        }
        String[] split = effectScopeIds.split(",");
        List<Integer> effectScopes = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
        return Lists.newArrayList(effectScopes);
    }

}
