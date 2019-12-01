package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOASignRank implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3320451340915948517L;
	private Integer id;
	private Integer userId;
	private String phone;
	private Integer storeId;
	private String createTime;
	private String signMonth;
	private String signDay;
	private Integer signNum;
	private Integer pointsNum;
	private Integer totalNum;
	private Integer totalPoints;
	private Integer status;
	private String userImg;
	
	private String signRank;
	
	
	
	public String getSignRank() {
		return signRank;
	}


	public void setSignRank(String signRank) {
		this.signRank = signRank;
	}


	public SOASignRank() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SOASignRank(Integer userId) {
		super();
		this.userId = userId;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSignMonth() {
		return signMonth;
	}
	public void setSignMonth(String signMonth) {
		this.signMonth = signMonth;
	}
	public String getSignDay() {
		return signDay;
	}
	public void setSignDay(String signDay) {
		this.signDay = signDay;
	}
	public Integer getSignNum() {
		return signNum;
	}
	public void setSignNum(Integer signNum) {
		this.signNum = signNum;
	}
	public Integer getPointsNum() {
		return pointsNum;
	}
	public void setPointsNum(Integer pointsNum) {
		this.pointsNum = pointsNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getUserImg() {
		return userImg;
	}


	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SOASignRank other = (SOASignRank) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SOASignRank [id=" + id + ", userId=" + userId + ", phone="
				+ phone + ", storeId=" + storeId + ", createTime=" + createTime
				+ ", signMonth=" + signMonth + ", signDay=" + signDay
				+ ", signNum=" + signNum + ", pointsNum=" + pointsNum
				+ ", totalNum=" + totalNum + ", totalPoints=" + totalPoints
				+ ", status=" + status + "]";
	}
	
	
}
