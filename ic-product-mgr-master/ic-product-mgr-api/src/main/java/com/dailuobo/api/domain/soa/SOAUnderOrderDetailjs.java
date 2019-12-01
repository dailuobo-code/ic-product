package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAOrderDetailjs.
 * 结算订单详情对象
 */
public class SOAUnderOrderDetailjs implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1064582303923842063L;
	
	/** The id. */
	private Integer id;
	
	/** The order id. */
	private String orderId;
	
	/** The spec id. */
	private Integer specId;
	
	/** The user id. */
	private Integer userId;
	
	/** The city id. */
	private Integer cityId;
	
	/** The store id. */
	private Integer storeId;
	
	/** The city product id. */
	private Integer cityProductId;
	
	/** The prod show name. */
	private String prodShowName;
	
	/** The prod icon. */
	private String prodIcon;
	
	/** The spec name. */
	private String specName;
	
	/** The weight. */
	private Integer weight;
	
	/** The price. */
	private Integer price;
	
	/** The actual weight. */
	private Integer actualWeight;
	
	/** The actual price. */
	private Integer actualPrice;
	
	/** The create time. */
	private String createTime;
	
	/** The update time. */
	private String updateTime;
	
	/** The change flag. */
	private Integer changeFlag;
	
	/** The create user id. */
	private Integer createUserId;
	
	/** 0 缺货  1 正常   2 退货 */
	private Integer isOutStock;
	
	private Integer unitType;
	
	private String avgUnit;
	
	
	
	public Integer getUnitType() {
		return unitType;
	}

	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}

	public String getAvgUnit() {
		return avgUnit;
	}

	public void setAvgUnit(String avgUnit) {
		this.avgUnit = avgUnit;
	}

	/**
	 * 获取  id.
	 *
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 设置 id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取  orderId.
	 *
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * 设置 orderId.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 获取  specId.
	 *
	 * @return specId
	 */
	public Integer getSpecId() {
		return specId;
	}
	
	/**
	 * 设置 specId.
	 *
	 * @param specId the new spec id
	 */
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	
	/**
	 * 获取  userId.
	 *
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * 设置 userId.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取  cityId.
	 *
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	
	/**
	 * 设置 cityId.
	 *
	 * @param cityId the new city id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	/**
	 * 获取  storeId.
	 *
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}
	
	/**
	 * 设置 storeId.
	 *
	 * @param storeId the new store id
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	/**
	 * 获取  cityProductId.
	 *
	 * @return cityProductId
	 */
	public Integer getCityProductId() {
		return cityProductId;
	}
	
	/**
	 * 设置 cityProductId.
	 *
	 * @param cityProductId the new city product id
	 */
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	
	/**
	 * 获取  prodShowName.
	 *
	 * @return prodShowName
	 */
	public String getProdShowName() {
		return prodShowName;
	}
	
	/**
	 * 设置 prodShowName.
	 *
	 * @param prodShowName the new prod show name
	 */
	public void setProdShowName(String prodShowName) {
		this.prodShowName = prodShowName;
	}
	
	/**
	 * 获取  prodIcon.
	 *
	 * @return prodIcon
	 */
	public String getProdIcon() {
		return prodIcon;
	}
	
	/**
	 * 设置 prodIcon.
	 *
	 * @param prodIcon the new prod icon
	 */
	public void setProdIcon(String prodIcon) {
		this.prodIcon = prodIcon;
	}
	
	/**
	 * 获取  specName.
	 *
	 * @return specName
	 */
	public String getSpecName() {
		return specName;
	}
	
	/**
	 * 设置 specName.
	 *
	 * @param specName the new spec name
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	
	/**
	 * 获取  weight.
	 *
	 * @return weight
	 */
	public Integer getWeight() {
		return weight;
	}
	
	/**
	 * 设置 weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	/**
	 * 获取  price.
	 *
	 * @return price
	 */
	public Integer getPrice() {
		return price;
	}
	
	/**
	 * 设置 price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	/**
	 * 获取  actualWeight.
	 *
	 * @return actualWeight
	 */
	public Integer getActualWeight() {
		return actualWeight;
	}
	
	/**
	 * 设置 actualWeight.
	 *
	 * @param actualWeight the new actual weight
	 */
	public void setActualWeight(Integer actualWeight) {
		this.actualWeight = actualWeight;
	}
	
	/**
	 * 获取  actualPrice.
	 *
	 * @return actualPrice
	 */
	public Integer getActualPrice() {
		return actualPrice;
	}
	
	/**
	 * 设置 actualPrice.
	 *
	 * @param actualPrice the new actual price
	 */
	public void setActualPrice(Integer actualPrice) {
		this.actualPrice = actualPrice;
	}
	
	/**
	 * 获取  createTime.
	 *
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置 createTime.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取  updateTime.
	 *
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置 updateTime.
	 *
	 * @param updateTime the new update time
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取  changeFlag
	 * @return changeFlag
	 */
	public Integer getChangeFlag() {
		return changeFlag;
	}

	/**
	 * 设置 changeFlag
	 * @param changeFlag
	 */
	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}

	/**
	 * 获取  createUserId
	 * @return createUserId
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置 createUserId
	 * @param createUserId
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 获取  isOutStock
	 * @return isOutStock
	 */
	public Integer getIsOutStock() {
		return isOutStock;
	}

	/**
	 * 设置 isOutStock
	 * @param isOutStock
	 */
	public void setIsOutStock(Integer isOutStock) {
		this.isOutStock = isOutStock;
	}
}
