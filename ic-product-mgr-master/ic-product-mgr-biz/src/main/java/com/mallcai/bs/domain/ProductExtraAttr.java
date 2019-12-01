package com.mallcai.bs.domain;

import com.mallcai.service.enums.ProductExtraAttrTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author wangshifeng
 * @date 2019-07-25 11:42
 */
@Data
public class ProductExtraAttr {

    /**
     * 属性id
     */
    private Integer id;

    /**
     * 城市商品id
     */
    private Integer cityProductId;

    /**
     * 属性类型
     */
    private ProductExtraAttrTypeEnum attrType;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 属性说明
     */
    private String attrDesc;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private Date createTime;
}
