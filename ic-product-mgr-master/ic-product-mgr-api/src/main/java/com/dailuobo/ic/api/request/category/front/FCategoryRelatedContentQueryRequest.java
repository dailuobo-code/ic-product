package com.dailuobo.ic.api.request.category.front;

import com.dailuobo.ic.api.base.CityBaseRequest;
import com.dailuobo.ic.api.enums.CategoryRelationTypeEnum;
import lombok.*;

import java.math.BigInteger;

/**
 * 前台类目关联内容查询请求
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FCategoryRelatedContentQueryRequest extends CityBaseRequest {
    private static final long serialVersionUID = -8536127660721910053L;

    /**
     * 关联Id
     */
    private BigInteger relationId;
    /**
     * 二级前台类目Id
     */
    private BigInteger frontCategoryId;

    /**
     * 关联类型
     *
     */
    private Integer relationType;

    @Override
    public boolean checkParams() {
        if (relationId == null|| relationType == null) {
            throw new IllegalArgumentException("参数缺失，请检查参数");
        }
        if (CategoryRelationTypeEnum.getByStatus(relationType) == null) {
            throw new IllegalArgumentException("relationType异常，请检查参数");
        }
        return true;
    }
}
