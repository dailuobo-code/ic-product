package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOACityProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 479305434765262986L;
	
	private Integer undSpecId; 
    private Integer cityProductId;
    private Integer cityId;
    private Integer hqStatus;
    private Integer cityStatus;
    private String showName;
    private String productNo;
    private Double advisePrice;
    private Double disablePrice;
    private String weightUnit;
    private String numUnit;
    private String cityProductIcon;
    private String cityProductPic;
    private String origin;
    private String detailUrl;
    private String subtitle;
    private String keyword;
    private String iconTip;
    private String l1L2Names;
    private Integer changeFlag;
    private Double delta;
    private Integer upperLimit;
    private Integer lowerLimit;
    private int isShare;//是否共享
    private Integer newUserPro; //新人专享  0不是，1是
    private Integer isUnder;
    private String barCode;
    private String hqProductName;
    
    private Integer updateUserId;
    private Integer createUserId;
    
    
    
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
	public String getHqProductName() {
		return hqProductName;
	}
	public void setHqProductName(String hqProductName) {
		this.hqProductName = hqProductName;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public Integer getUndSpecId() {
		return undSpecId;
	}
	public void setUndSpecId(Integer undSpecId) {
		this.undSpecId = undSpecId;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getHqStatus() {
		return hqStatus;
	}
	public void setHqStatus(Integer hqStatus) {
		this.hqStatus = hqStatus;
	}
	public Integer getCityStatus() {
		return cityStatus;
	}
	public void setCityStatus(Integer cityStatus) {
		this.cityStatus = cityStatus;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public Double getAdvisePrice() {
		return advisePrice;
	}
	public void setAdvisePrice(Double advisePrice) {
		this.advisePrice = advisePrice;
	}
	public Double getDisablePrice() {
		return disablePrice;
	}
	public void setDisablePrice(Double disablePrice) {
		this.disablePrice = disablePrice;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getNumUnit() {
		return numUnit;
	}
	public void setNumUnit(String numUnit) {
		this.numUnit = numUnit;
	}
	public String getCityProductIcon() {
		return cityProductIcon;
	}
	public void setCityProductIcon(String cityProductIcon) {
		this.cityProductIcon = cityProductIcon;
	}
	public String getCityProductPic() {
		return cityProductPic;
	}
	public void setCityProductPic(String cityProductPic) {
		this.cityProductPic = cityProductPic;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getIconTip() {
		return iconTip;
	}
	public void setIconTip(String iconTip) {
		this.iconTip = iconTip;
	}
	public String getL1L2Names() {
		return l1L2Names;
	}
	public void setL1L2Names(String l1l2Names) {
		l1L2Names = l1l2Names;
	}
	public Integer getChangeFlag() {
		return changeFlag;
	}
	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}
	public Double getDelta() {
		return delta;
	}
	public void setDelta(Double delta) {
		this.delta = delta;
	}
	public Integer getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}
	public Integer getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public int getIsShare() {
		return isShare;
	}
	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}
	public Integer getNewUserPro() {
		return newUserPro;
	}
	public void setNewUserPro(Integer newUserPro) {
		this.newUserPro = newUserPro;
	}
	public Integer getIsUnder() {
		return isUnder;
	}
	public void setIsUnder(Integer isUnder) {
		this.isUnder = isUnder;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
