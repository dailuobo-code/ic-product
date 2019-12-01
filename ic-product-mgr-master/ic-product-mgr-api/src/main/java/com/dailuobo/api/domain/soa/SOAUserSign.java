package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAUserSign implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8075507793658506623L;
/*	   `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户手机号码',
  `store_id` int(11) DEFAULT NULL COMMENT '签到门店',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '签到时间',
  `sign_month` date DEFAULT NULL COMMENT '签到月份（2018-07）',
  `sign_day` date DEFAULT NULL COMMENT '签到日期（2018-07-18）',
  `sign_num` int(11) DEFAULT NULL COMMENT '连续签到次数',
  `total_num` int(11) DEFAULT NULL COMMENT '累计签到次数',
  `points_num` int(11) DEFAULT NULL COMMENT '获得积分数量',
  `total_points` int(11) DEFAULT NULL COMMENT '签到累计积分数量',*/
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
	
	
	

}
