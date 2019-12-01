package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HqProduct extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer hqProductId;
    private String productNo;
    private String hqProductName;
    private String remark;
    private Integer status;
    private String hqProductPic;
    private String hqProductIcon;
    private Classify classify;
    private Integer localizeFlag;
    private String c1Name;
    private String c2Name;
    private Byte deliveryMode;
    private String barCode;
    private Integer val;
    private Integer keepType;
    private Integer qualityTime;
    private Integer isStandard;
    private Integer pickClassify;
    private BigDecimal productRate;
    private String length;
    private String wides;
    private String high;
    private String fatherName;
    private Integer fatherId;
    private String  classifyName;
    private Integer isFull;
    private Integer goodsType;
    private Integer productTaxId;
    private String taxClassifyCode;
    private String auditStatus;

    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
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

    public Integer getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(Integer isStandard) {
        this.isStandard = isStandard;
    }

    public Integer getQualityTime() {
		return qualityTime;
	}

	public void setQualityTime(Integer qualityTime) {
		this.qualityTime = qualityTime;
	}

	public HqProduct() {
    }

    public HqProduct(Integer hqProductId) {
        this.hqProductId = hqProductId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getHqProductId() {
        return hqProductId;
    }

    public void setHqProductId(Integer hqProductId) {
        this.hqProductId = hqProductId;
    }

    public String getHqProductName() {
        return hqProductName;
    }

    public void setHqProductName(String hqProductName) {
        this.hqProductName = hqProductName;
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

    public String getHqProductPic() {
        return hqProductPic;
    }

    public void setHqProductPic(String hqProductPic) {
        this.hqProductPic = hqProductPic;
    }

    public String getHqProductIcon() {
        return hqProductIcon;
    }

    public void setHqProductIcon(String hqProductIcon) {
        this.hqProductIcon = hqProductIcon;
    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }

    public Integer getLocalizeFlag() {
        return localizeFlag;
    }

    public void setLocalizeFlag(Integer localizeFlag) {
        this.localizeFlag = localizeFlag;
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

    public Byte getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Byte deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public Integer getKeepType() {
		return keepType;
	}

	public void setKeepType(Integer keepType) {
		this.keepType = keepType;
	}

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWides() {
        return wides;
    }

    public void setWides(String wides) {
        this.wides = wides;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getProductTaxId() {
        return productTaxId;
    }

    public void setProductTaxId(Integer productTaxId) {
        this.productTaxId = productTaxId;
    }

    public String getTaxClassifyCode() {
        return taxClassifyCode;
    }

    public void setTaxClassifyCode(String taxClassifyCode) {
        this.taxClassifyCode = taxClassifyCode;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
