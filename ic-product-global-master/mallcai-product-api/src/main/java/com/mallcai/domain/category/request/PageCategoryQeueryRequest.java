package com.mallcai.domain.category.request;

import com.mallcai.domain.RequestEntity;

public class PageCategoryQeueryRequest extends RequestEntity {

    private static final long serialVersionUID = -4562025906850716395L;
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
    private Integer parentId;


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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
