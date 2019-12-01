package com.mallcai.domain.category.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class CategoryQueryRequest implements Serializable {
    private static final long serialVersionUID = 274655171353870609L;

    /**
     * 分类等级
     */
    private Integer level;
    /**
     * 分类id
     */
    private Integer classifyId;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 分类编号
     */
    private String classifyNo;
    /**
     * 父级分类id
     */
    private Integer fatherId;


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyNo() {
        return classifyNo;
    }

    public void setClassifyNo(String classifyNo) {
        this.classifyNo = classifyNo;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);

    }
}
