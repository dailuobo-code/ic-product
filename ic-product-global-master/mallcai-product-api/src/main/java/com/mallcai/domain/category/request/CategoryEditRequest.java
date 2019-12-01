package com.mallcai.domain.category.request;

import com.mallcai.domain.enums.OperationEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 分类编辑请求
 */
public class CategoryEditRequest implements Serializable {
    private static final long serialVersionUID = 6311239943747746694L;

    /**
     * 商品分类主见Id
     */
    private Integer classifyId;
    /**
     * 分类等级
     */
    private Integer level;
    /**
     * 分类编号
     */
    private String classifyNo;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 图标地址
     */
    private String iconUrl;
    /**
     * 父级分类id
     */
    private Integer fatherId;
    /**
     * 取货排序
     */
    private Integer pickupOrder;
    /**
     * 展示排序
     */
    private Integer showOrder;
    /**
     * 创建人
     */
    private Integer  createUserId;
    /**
     * 修改人
     */
    private Integer updateUserId;

    /**
     * 操作类型，必填
     * OperationEnum.ADD    新增
     * OperationEnum.EDIT   编辑
     */
    private OperationEnum operationEnum;

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public OperationEnum getOperationEnum() {
        return operationEnum;
    }

    public void setOperationEnum(OperationEnum operationEnum) {
        this.operationEnum = operationEnum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }


}
