package com.mallcai.domain.product.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ModifyProductRequest implements Serializable {

    private static final long serialVersionUID = -8973900913626318830L;

    /**
     * db id
     */
    private Integer productId;
    /**
     * 二级类目ID
     */
    private Integer categoryId;
    /**
     * 商品描述
     */
    private String remark;
    /**
     * 商品icon
     */
    private String productIcon;
    /**
     * 商品图片
     */
    private List<String> productPics;
    /**
     * 商品条形码
     */
    private String barCode;
    /**
     * 储存方式,1-常温 2-冷藏 3-冷冻
     */
    private Integer keepType;
    /**
     * 配送方式 1:门店自提，2:送货上门
     */
    private Integer deliveryMode;

    /**
     * 商品类型 1:普通商品，2:新人专享商品
     */
    private Integer goodsType;
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
     * 更新人
     */
    private Integer userId;

    /**
     * 税收分类编码
     */
    private Integer productTaxId;

    private String c1Name;
    private String c2Name;
    private Integer c2Old;

    /**
     * 是否走采销预测
     * 0表示否 1表示是
     */
    private Integer isForecast;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 售卖季节性
     */
    private Integer seasonal;

    /**
     * 发货时间
     * 配送方式,2:送货上门 值不可为空
     */
    private Integer arrivalDay;

    private Long skuId;
    private Long itemId;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public List<String> getProductPics() {
        return productPics;
    }

    public void setProductPics(List<String> productPics) {
        this.productPics = productPics;
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

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductTaxId() {
        return productTaxId;
    }

    public void setProductTaxId(Integer productTaxId) {
        this.productTaxId = productTaxId;
    }

    public String getC1Name() {
        return c1Name;
    }

    public void setC1Name(String c1Name) {
        this.c1Name = c1Name;
    }

    public String getC2Name() {
        return c2Name;
    }

    public void setC2Name(String c2Name) {
        this.c2Name = c2Name;
    }

    public Integer getC2Old() {
        return c2Old;
    }

    public void setC2Old(Integer c2Old) {
        this.c2Old = c2Old;
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

    public Integer getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(Integer arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

}
