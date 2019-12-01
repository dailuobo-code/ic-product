package com.mallcai.domain.category.request;

import java.io.Serializable;

public class GetHierarchicalCategoryRequest implements Serializable {
    private static final long serialVersionUID = 8012956231027549597L;
    /**
     * 分类级别,1:一级,2:二级
     */
    private Integer level;
    /**
     * 父类目id
     */
    private Integer parentId;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
