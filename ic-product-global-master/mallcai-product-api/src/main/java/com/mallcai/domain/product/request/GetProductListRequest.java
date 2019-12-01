package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;
import com.mallcai.domain.enums.AuditStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class GetProductListRequest extends RequestEntity implements Serializable {

    /**
     * 二级类目ID
     */
    private Integer categoryId;
    /**
     * 一级类目ID
     */
    private Integer parentCategoryId;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 总部状态 0:下架 1:上架
     */
    private List<Byte> status;
    /**
     * 是否标品 0-未维护的 1-是 2-否
     */
    private Integer isStandard;
    /**
     * 门店取货标签
     */
    private Integer pickClassify;
    /**
     * 商品类型 1:普通商品，2:新人专享
     */
    private Integer productType;
    /**
     * 配送方式 1:门店自提，2:送货上门
     */
    private Integer deliveryMode;
    /**
     * 审批状态
     */
    @Getter
    @Setter
    private AuditStatus auditStatus;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 售卖季节性
     */
    private Integer seasonal;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Byte> getStatus() {
        return status;
    }

    public void setStatus(List<Byte> status) {
        this.status = status;
    }

    public Integer getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(Integer isStandard) {
        this.isStandard = isStandard;
    }

    public Integer getPickClassify() {
        return pickClassify;
    }

    public void setPickClassify(Integer pickClassify) {
        this.pickClassify = pickClassify;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Integer getNewArrivalType() {
        return newArrivalType;
    }

    public void setNewArrivalType(Integer newArrivalType) {
        this.newArrivalType = newArrivalType;
    }

    public Integer getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(Integer seasonal) {
        this.seasonal = seasonal;
    }
}
