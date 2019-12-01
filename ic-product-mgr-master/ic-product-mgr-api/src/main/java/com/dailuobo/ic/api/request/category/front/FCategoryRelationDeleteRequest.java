package com.dailuobo.ic.api.request.category.front;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.*;

import java.math.BigInteger;

/**
 * 前台类目跟后台类目/商品关联关系删除请求
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FCategoryRelationDeleteRequest extends CityBaseRequest {

    /**
     * 关联关系Id
     */
    private BigInteger relationId;
    /**
     * 前台类二级目Id
     */
    private BigInteger fCategoryId;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 关联类型：1。后台分类 2。 城市商品
     */
    private Integer relationType;


    @Override
    public boolean checkParams() {
        if (relationId == null || operatorId == null) {
            throw new IllegalArgumentException("参数错误，请检查参数");
        }
        return true;
    }
}
