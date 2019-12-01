package com.mallcai.domain.dataobject.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mallcai.BaseEntity;
import lombok.Data;

/**
 * 商品后台分类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDO extends BaseEntity {

    public CategoryDO(){}
    public CategoryDO(Integer classifyId){
        this.classifyId =classifyId;
    }

    /**
     * 分类Id
     */
    private Integer classifyId;
    /**
     * 分类No
     */
    private String classifyNo;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 删除标示
     * 0:删除
     * 1：正常
     */
    private Byte delFlag;
    /**
     * iconurl
     */
    private String iconUrl;
    /**
     * 级别 1：一级  |  2：二级
     */
    private Integer level;
    /**
     * 父级Id
     */
    private Integer fatherId;

    private String fatherName;
    
    /**
     * 取货排序
     */
    private Integer pickupOrder;
    /**
     * 展示排序
     */
    private Integer showOrder;

}
