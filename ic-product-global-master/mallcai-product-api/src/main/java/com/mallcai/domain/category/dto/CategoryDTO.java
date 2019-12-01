package com.mallcai.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mallcai.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5428140872569190996L;
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


    /**
     * 父级类目名称
     */
    private String fatherName;
    /**
     * 取货排序
     */
    private Integer pickupOrder;
    /**
     * 展示排序
     */
    private Integer showOrder;

    /**
     * 子分类列表
     */
    private List<CategoryDTO> classifyList;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer classifyId) {
        if (classifyId == null) {
            this.classifyId = 0;
        } else {
            this.classifyId = classifyId;
        }
    }
}
