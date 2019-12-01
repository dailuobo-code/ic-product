package com.dailuobo.ic.api.request.category.front;

import com.dailuobo.ic.api.base.ICBaseRequest;
import lombok.*;

import java.math.BigInteger;

/**
 * 前台类目关联的后台类目
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FCategoryRelatedBCategory extends ICBaseRequest {

    /**
     * 后台二级类目Id
     */
    private Integer categoryId;

    /**
     * 展示顺序
     */
    private Integer displayOrder=1;

    /**
     * 操作人Id
     */
    private Integer operatorId;

}
