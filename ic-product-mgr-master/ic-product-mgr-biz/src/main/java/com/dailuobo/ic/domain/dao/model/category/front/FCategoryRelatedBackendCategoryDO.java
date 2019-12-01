package com.dailuobo.ic.domain.dao.model.category.front;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

/**
 * 前台类目关联的后台类目模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FCategoryRelatedBackendCategoryDO {
    /**
     * 主键id
     */
    private BigInteger id;
    /**
     * 关联关系Id
     */
    private BigInteger relationId;


    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 后台类目Id
     */
    private Integer backendCategoryId;
    /**
     * 展示序列
     */
    private Integer displayOrder;

    /**
     * 创建人Id
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer updateUserId;

    /**
     * 修改时间
     */
    private Integer updateTime;

    /**
     * 删除标示
     */
    private Integer isDeleted;
}
