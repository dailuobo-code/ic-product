package com.dailuobo.ic.domain.dao.model.redis;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 前台类目关联的对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryRelatedEntity implements Serializable {
    /**
     * 实体id
     */
    private Integer id;
    /**
     * 排序
     */
    private Integer order;
}
