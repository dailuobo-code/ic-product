/**
 * User.java
 * @author huangwei
 * @since 2016-1-25
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

/**
 * User.java
 * @author huangwei
 * @since 2016-1-25
 *  描述：
 *  App用户实体
 */
public class SOAAppUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6878055812185779595L;

	/** 用户id */
	private Integer userId;
	
	/** 手机号码 */
	private String phone;
	
	/** 昵称 */
	private String nickname;
	
	/** 性别(0:男,1:女) */
	private Integer sex;
	
	/** 生日 */
	private Date birthday;
	
	/** 用户真实姓名 */
	private String userRealName;
	
	/** 城市id */
	private Integer cityId;
	
	/** 等级id */
	private Integer levelId;
	
	/** 小区id */
	private Integer residenceId;
	
	/** 账号状态 '0'-正常  '1'-冻结 */
	private String status;
	
	/** 账号冻结时间*/
	private Date frozenTime;
	
	/** 账户余额 */
	private Integer accountBalance;
	
	/** 优惠券数量 */
	private Integer couponsNum;
	
	/** 友好度 */
	private Integer friendlyDegree;
	
	/** 积分 */
	private Integer points;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 更新数据的用户id来源tbl_user表 */
	private Integer updateUserId;
	
	/** 用户认证状态  '0'-未认证 '1'-第一阶段 '2'-第二阶段 '3'-第三阶段 '4'-认证通过 '5'认证不通过 */
	private Integer authStatus;
	
	/** 用户头像url */
	private String profileImageUrl;
	
	/** 用户成长值 */
	private Integer growth;
	
	/** 取货点id */
	private Integer storeId;
	
	/** 城市名 */
	private String cityName;
	
	/** 取货点名称 */
	private String storeName;
	
	private String jpushName;//极光推送的用户别名
	
	private String openid;
	
	private String platformName;
	
	private String wxNickname;

	private Integer parentUserId;
	
	private String inviteCode;
	
	private Integer isNew;//是否是新用户 1是，0否

	private String device;

	//是否是团长 0 否 1 是 2 团长冻结 3 待审核 4 审核不通过
	private Integer groupStatus;
	
	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Integer parentUserId) {
		this.parentUserId = parentUserId;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	/**
	 * @return the storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the userRealName
	 */
	public String getUserRealName() {
		return userRealName;
	}

	/**
	 * @param userRealName the userRealName to set
	 */
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the levelId
	 */
	public Integer getLevelId() {
		return levelId;
	}

	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	/**
	 * @return the residenceId
	 */
	public Integer getResidenceId() {
		return residenceId;
	}

	/**
	 * @param residenceId the residenceId to set
	 */
	public void setResidenceId(Integer residenceId) {
		this.residenceId = residenceId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the frozenTime
	 */
	public Date getFrozenTime() {
		return frozenTime;
	}

	/**
	 * @param frozenTime the frozenTime to set
	 */
	public void setFrozenTime(Date frozenTime) {
		this.frozenTime = frozenTime;
	}

	/**
	 * @return the accountBalance
	 */
	public Integer getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the couponsNum
	 */
	public Integer getCouponsNum() {
		return couponsNum;
	}

	/**
	 * @param couponsNum the couponsNum to set
	 */
	public void setCouponsNum(Integer couponsNum) {
		this.couponsNum = couponsNum;
	}

	/**
	 * @return the friendlyDegree
	 */
	public Integer getFriendlyDegree() {
		return friendlyDegree;
	}

	/**
	 * @param friendlyDegree the friendlyDegree to set
	 */
	public void setFriendlyDegree(Integer friendlyDegree) {
		this.friendlyDegree = friendlyDegree;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the updateUserId
	 */
	public Integer getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId the updateUserId to set
	 */
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @return the authStatus
	 */
	public Integer getAuthStatus() {
		return authStatus;
	}

	/**
	 * @param authStatus the authStatus to set
	 */
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	/**
	 * @return the profileImageUrl
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	/**
	 * @param profileImageUrl the profileImageUrl to set
	 */
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	/**
	 * @return the growth
	 */
	public Integer getGrowth() {
		return growth;
	}

	/**
	 * @param growth the growth to set
	 */
	public void setGrowth(Integer growth) {
		this.growth = growth;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取  cityName
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 设置 cityName
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 获取  storeName
	 * @return storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 设置 storeName
	 * @param storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 获取  jpushName
	 * @return jpushName
	 */
	public String getJpushName() {
		return jpushName;
	}

	/**
	 * 设置 jpushName
	 * @param jpushName
	 */
	public void setJpushName(String jpushName) {
		this.jpushName = jpushName;
	}

	/**
	 * 获取  openid
	 * @return openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * 设置 openid
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 获取  platformName
	 * @return platformName
	 */
	public String getPlatformName() {
		return platformName;
	}

	/**
	 * 设置 platformName
	 * @param platformName
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	/**
	 * 获取  wxNickname
	 * @return wxNickname
	 */
	public String getWxNickname() {
		return wxNickname;
	}

	/**
	 * 设置 wxNickname
	 * @param wxNickname
	 */
	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Integer getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(Integer groupStatus) {
		this.groupStatus = groupStatus;
	}
}
