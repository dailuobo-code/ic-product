package com.dailuobo.api.domain.util;

import java.io.Serializable;

/**
 * @Title: ExcelData.java
 * @Description: TODO(这里用一句话描述这个类/页面的作用)
 * @author wanghb@mallcai.com （汪海波）
 * @date 2018年7月6日上午10:43:54
 * @version V1.0
 * @Revision : Rev
 * @Id: Id
 *
 * @Company: 安徽菜菜电子商务有限公司
 * @Copyright: Copyright (c) 2018
 */
public class ExcelData implements Serializable{

	/**
	 *
	 */
	public static final long serialVersionUID = 4894110156150830063L;
	/**
	 * 商品编号
	 */
	public String productCode;
	/**
	 * 商品名称
	 */
	public String productName;
	/**
	 * 项目代码
	 */
	public String projectCode;
	/**
	 * 计重标识
	 */
	public String calFlag;
	/**
	 * 单价
	 */
	public String unitPrice;
	/**
	 * 贮存方式
	 */
	public String keepType;
	/**
	 * 上限
	 */
	public String upperLimit;
	/**
	 * 下限
	 */
	public String lowerLimit;
	/**
	 * 保质期
	 */

	public String quality;

	public String qualityTime;
	/**
	 * 销售商
	 */
	public String saleName;

	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getQualityTime() {
		return qualityTime;
	}
	public void setQualityTime(String qualityTime) {
		this.qualityTime = qualityTime;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getCalFlag() {
		return calFlag;
	}
	public void setCalFlag(String calFlag) {
		this.calFlag = calFlag;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getKeepType() {
		return keepType;
	}
	public void setKeepType(String keepType) {
		this.keepType = keepType;
	}
	public String getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}
	public String getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
		return "ExcelData{" +
				"productCode='" + productCode + '\'' +
				", productName='" + productName + '\'' +
				", projectCode='" + projectCode + '\'' +
				", calFlag='" + calFlag + '\'' +
				", unitPrice='" + unitPrice + '\'' +
				", keepType='" + keepType + '\'' +
				", upperLimit='" + upperLimit + '\'' +
				", lowerLimit='" + lowerLimit + '\'' +
				", qualityTime='" + qualityTime + '\'' +
				", saleName='" + saleName + '\'' +
				'}';
	}
}
