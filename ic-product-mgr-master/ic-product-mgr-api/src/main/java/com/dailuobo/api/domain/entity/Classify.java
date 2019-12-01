package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Classify extends BaseEntity implements Comparable<Classify>,Serializable {
    private static final long serialVersionUID = 1L;
    private Integer classifyId;
    private String classifyNo;
    private String classifyName;
    private Byte delFlag;
    private String iconUrl;
    private Integer level;
    private Classify parent;
    private Integer pickupOrder;
    private Integer showOrder;

    private List<Classify> classifyList;

    public List<Classify> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<Classify> classifyList) {
        this.classifyList = classifyList;
    }

    public Classify() {
    }

    public Classify(Integer classifyId) {
        if (classifyId == null) {
            this.classifyId = 0;
        } else {
            this.classifyId = classifyId;
        }
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyNo() {
        return classifyNo;
    }

    public void setClassifyNo(String classifyNo) {
        this.classifyNo = classifyNo;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Classify getParent() {
        return parent;
    }

    public void setParent(Classify parent) {
        this.parent = parent;
    }

    public Integer getPickupOrder() {
        return pickupOrder;
    }

    public void setPickupOrder(Integer pickupOrder) {
        this.pickupOrder = pickupOrder;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    @Override
    public int compareTo(Classify o) {
        if (level == 1) {
            if (o.level == 1) {
                if (showOrder.intValue() == o.showOrder.intValue()) {
                    return classifyId - o.classifyId;
                } else {
                    return showOrder - o.showOrder;
                }
            } else {
                if (showOrder.intValue() == o.parent.showOrder.intValue()) {
                    return classifyId - o.parent.classifyId;
                } else {
                    return showOrder - o.parent.showOrder;
                }
            }
        } else {
            if (o.level == 1) {
                if (parent.showOrder.intValue() == o.showOrder.intValue()) {
                    return parent.classifyId - o.classifyId;
                } else {
                    return parent.showOrder - o.showOrder;
                }
            } else {
                if (parent.showOrder.intValue() == o.parent.showOrder.intValue()) {
                    return parent.classifyId - o.parent.classifyId;
                } else {
                    return parent.showOrder - o.parent.showOrder;
                }
            }
        }
    }
}