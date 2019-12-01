package com.dailuobo.api.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
*  萝卜拼商品
 */
public class PieceProduct extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pieceTitle;
	private Integer cityId;
	private Integer cityProductId;
	private Integer pieceType;
	private Integer peopleNum;
	private BigDecimal alonePrice;
	private Integer initialNum;
	private String productPic;
	private Integer status;
	private String productName;


	private String tag;

	private Integer deleteUserId;

	private Integer available;
	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPieceTitle() {
		return pieceTitle;
	}

	public void setPieceTitle(String pieceTitle) {
		this.pieceTitle = pieceTitle;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCityProductId() {
		return cityProductId;
	}

	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}

	public Integer getPieceType() {
		return pieceType;
	}

	public void setPieceType(Integer pieceType) {
		this.pieceType = pieceType;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public BigDecimal getAlonePrice() {
		return alonePrice;
	}

	public void setAlonePrice(BigDecimal alonePrice) {
		this.alonePrice = alonePrice;
	}

	public Integer getInitialNum() {
		return initialNum;
	}

	public void setInitialNum(Integer initialNum) {
		this.initialNum = initialNum;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getDeleteUserId() {
		return deleteUserId;
	}

	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
	}


	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
