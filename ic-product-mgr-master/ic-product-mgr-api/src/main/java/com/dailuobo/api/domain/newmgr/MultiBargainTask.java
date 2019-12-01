package com.dailuobo.api.domain.newmgr;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cowboy on 2016/10/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultiBargainTask implements Serializable {
    private static final long serialVersionUID = -4204100760666154808L;
    private Integer id;
    /**
     * 任务执行时间
     */
    private Date executeTime;
    /**
     * 城市商品id
     */
    private Integer cityProductId;
    /**
     * 商品编号 skuid
     */
    private String productNo;
    private String showName;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 店铺ids
     */
    private String storeIds;
    /**
     * 店铺名
     */
    private String storeNames;

    private String iconTip;
    private String keyword;
    /**
     * 商品单价
     */
    private Double avgPrice;
    /**
     * 商品售价
     */
    private Double realPrice;
    /**
     * 单位
     */
    private String avgUnit;
    /**
     * 门店单价
     */
    private Double storeAvgPrice;
    /**
     * 门店售价
     */
    private Double storeRealPrice;
    /**
     * 限购数
     */
    private Integer thresholdForPurchase;
    private Date createTime;
    private Integer createUserId;
    /**
     * 创建人
     */
    private String createRealName;

    /**
     * 状态
     */
    private Integer status;
    private String exception;
    private Date updateTime;

    /**
     * 总部商品ICON
     */
    private String hqProductIcon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public String getStoreNames() {
        return storeNames;
    }

    public void setStoreNames(String storeNames) {
        this.storeNames = storeNames;
    }

    public String getIconTip() {
        return iconTip;
    }

    public void setIconTip(String iconTip) {
        this.iconTip = iconTip;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getThresholdForPurchase() {
        return thresholdForPurchase;
    }

    public void setThresholdForPurchase(Integer thresholdForPurchase) {
        this.thresholdForPurchase = thresholdForPurchase;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHqProductIcon() {
        return hqProductIcon;
    }

    public void setHqProductIcon(String hqProductIcon) {
        this.hqProductIcon = hqProductIcon;
    }

    public String getCreateRealName() {
        return createRealName;
    }

    public void setCreateRealName(String createRealName) {
        this.createRealName = createRealName;
    }

    public Double getStoreAvgPrice() {
        return storeAvgPrice;
    }

    public void setStoreAvgPrice(Double storeAvgPrice) {
        this.storeAvgPrice = storeAvgPrice;
    }

    public Double getStoreRealPrice() {
        return storeRealPrice;
    }

    public void setStoreRealPrice(Double storeRealPrice) {
        this.storeRealPrice = storeRealPrice;
    }

    public String getAvgUnit() {
        return avgUnit;
    }

    public void setAvgUnit(String avgUnit) {
        this.avgUnit = avgUnit;
    }
}
