package com.dailuobo.api.domain.vo;

import java.io.Serializable;

public class DDLClassify implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer classifyId;
    private String classifyName;

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
}
