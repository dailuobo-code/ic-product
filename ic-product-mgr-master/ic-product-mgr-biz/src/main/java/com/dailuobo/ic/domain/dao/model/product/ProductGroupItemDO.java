package com.dailuobo.ic.domain.dao.model.product;

import lombok.Data;

import java.util.Date;
@Data
public class ProductGroupItemDO {
    private Integer id;
    private Integer cityId;
    private Integer groupId;
    private Integer cityProductId;
    private Boolean deleted;
    private Date updateTime;
    private Date createTime;
}