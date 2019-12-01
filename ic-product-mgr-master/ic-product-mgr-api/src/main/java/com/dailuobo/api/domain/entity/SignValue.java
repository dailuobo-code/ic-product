package com.dailuobo.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ...
 *
 * @author lihf@mallcai.com <lihaifeng>
 * @date 2019-05-16 13:49
 * @since Jdk 1.8
 */
@Data
public class SignValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityId;

    private Integer cityProductId;

    private Integer storeId;

    private String pennySignValue;

    /**
     * 生效时间
     */
    private Date effectiveTime;
}
