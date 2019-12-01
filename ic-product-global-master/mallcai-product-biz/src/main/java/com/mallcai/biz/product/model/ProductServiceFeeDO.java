package com.mallcai.biz.product.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ServiceFeeTemplate
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 11:51<br/>
 */
@Data
public class ProductServiceFeeDO implements Serializable {
    private static final long serialVersionUID = -9091444601582330070L;

    private Long id;
    private Integer cityId;
    private Integer cityProductId;
    private Long templateId;
    private Integer operatorId;
    private String operator;
    private Date createTime;
    private Date updateTime;
}
