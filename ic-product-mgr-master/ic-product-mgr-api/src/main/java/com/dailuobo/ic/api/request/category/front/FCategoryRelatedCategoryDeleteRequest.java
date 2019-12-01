package com.dailuobo.ic.api.request.category.front;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.*;

import java.math.BigInteger;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FCategoryRelatedCategoryDeleteRequest extends CityBaseRequest {

    /**
     * 关联id
     */
    private BigInteger relationId;
    /**
     * 关联类型
     */
    private Integer relationType;

    /**
     * 前台二级类目Id
     */
    private BigInteger frontCategoryId;

}
