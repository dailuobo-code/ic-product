package com.mallcai.domain.product.dto;


import com.mallcai.domain.BaseEntity;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.enums.AuditStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1816496157898431634L;
    /**
     * db id
     */
    private Integer productId;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品描述
     */
    private String remark;
    /**
     * 总部状态 0:下架 1:上架
     */
    private Integer status;
    /**
     * 商品图片
     */
    private List<String> productPics;
    /**
     * 商品icon
     */
    private String productIcon;
    /**
     * 二级类目ID
     */
    private Integer categoryId;
    /**
     * 本市化 0:否 1:是
     */
    private Integer localizeFlag;
    /**
     * 配送方式,1:门店自提,2:送货上门
     */
    private Integer deliveryMode;
    /**
     * 商品条形码
     */
    private String barCode;
    /**
     * 储存方式,1-常温 2-冷藏 3-冷冻
     */
    private Integer keepType;
    /**
     * 保持期
     */
    private Integer qualityTime;
    /**
     * 是否标品 0-未维护的 1-是 2-否
     */
    private Integer isStandard;
    /**
     * 门店取货标签
     */
    private Integer pickClassify;
    /**
     * 税率
     */
    private BigDecimal productRate;
    /**
     * 长,单位m
     */
    private String length;
    /**
     * 宽,单位m
     */
    private String width;
    /**
     * 高,单位m
     */
    private String high;
    /**
     * 商品类型 1:普通商品，2:新人专享
     */
    private Integer productType;
    /**
     * 删除标志 0:删除 1:正常
     */
    private Integer delFlag;
    /**
     * 供应商发货到
     */
    private Integer supplierDeliverGoal;
    /**
     * 未知
     */
    private String taxClassifyCode;

    /**
     * 类目DTO
     */
    private CategoryDTO categoryDTO;

    /**
     * 税收分类
     */
    private Integer productTaxId;

    /**
     * 商品状态
     */
    @Getter
    @Setter
    private AuditStatus auditStatus;

    /**
     * 审批结果
     */
    @Getter
    @Setter
    private AuditResultDTO auditResult;

    /**
     * 是否走采销预测
     * 0表示否 1表示是
     */
    private Integer isForecast;


    /**
     * 多规格销售属性
     */
    private List<SkuAttributeDTO> attributes;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 0 非多规格  1 多规格
     * 是否是多规格商品
     */
    private Integer isMultiProduct;

    /**
     * 售卖季节性
     */
    private Integer seasonal;
    /**
     * 新商品 itemId 2019-10-21 09:48:004
     */
    private Long itemId;
    /**
     * skuId  2019-10-21 09:47:49
     */
    private Long skuId;


    /**
     * 发货时间属性 deliverMode等于2时有值
     */
    private ProductDeliveryHomeAttrDTO deliveryHomeAttrDTO;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getProductPics() {
        return productPics;
    }

    public void setProductPics(List<String> productPics) {
        this.productPics = productPics;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getLocalizeFlag() {
        return localizeFlag;
    }

    public void setLocalizeFlag(Integer localizeFlag) {
        this.localizeFlag = localizeFlag;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getKeepType() {
        return keepType;
    }

    public void setKeepType(Integer keepType) {
        this.keepType = keepType;
    }

    public Integer getQualityTime() {
        return qualityTime;
    }

    public void setQualityTime(Integer qualityTime) {
        this.qualityTime = qualityTime;
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

    public BigDecimal getProductRate() {
        return productRate;
    }

    public void setProductRate(BigDecimal productRate) {
        this.productRate = productRate;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getSupplierDeliverGoal() {
        return supplierDeliverGoal;
    }

    public void setSupplierDeliverGoal(Integer supplierDeliverGoal) {
        this.supplierDeliverGoal = supplierDeliverGoal;
    }

    public String getTaxClassifyCode() {
        return taxClassifyCode;
    }

    public void setTaxClassifyCode(String taxClassifyCode) {
        this.taxClassifyCode = taxClassifyCode;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public Integer getProductTaxId() {
        return productTaxId;
    }

    public void setProductTaxId(Integer productTaxId) {
        this.productTaxId = productTaxId;
    }

    public Integer getIsForecast() {
        return isForecast;
    }

    public void setIsForecast(Integer isForecast) {
        this.isForecast = isForecast;
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

    public ProductDeliveryHomeAttrDTO getDeliveryHomeAttrDTO() {
        return deliveryHomeAttrDTO;
    }

    public void setDeliveryHomeAttrDTO(ProductDeliveryHomeAttrDTO deliveryHomeAttrDTO) {
        this.deliveryHomeAttrDTO = deliveryHomeAttrDTO;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public AuditResultDTO getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(AuditResultDTO auditResult) {
        this.auditResult = auditResult;
    }

    public List<SkuAttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SkuAttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public Integer getIsMultiProduct() {
        if (this.isMultiProduct != null) {
            return isMultiProduct;
        }
        if (this.itemId > 0 && this.skuId > 0) {
            return 1;
        }
        return 0;
    }

    public void setIsMultiProduct(Integer isMultiProduct) {
        this.isMultiProduct = isMultiProduct;
    }


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}
