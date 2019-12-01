package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class AppUserInfo.
 */
public class AppUserInfo extends SOAAppUser implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1115061772648142136L;

    /**
     * 用户等级名称.
     */
    private String levelName;

    /**
     * The sex name.
     */
    private String sexName;

    /**
     * The store name.
     */
    private String storeName;

    /**
     * The auth status name.
     */
    private String authStatusName;

    /**
     * The residence name.
     */
    private String residenceName;

    /**
     * The city name.
     */
    private String cityName;

    private String buyTime;        //购买时间

    private String startDate;    //生效日期

    private String endDate;    //失效日期

    private Integer vipStatus;    //vip状态 0-非会员 1-生效会员 2-失效会员

    private Float vipPrice;    //vip优惠总金额

    private Integer buyVipStatus;   //购买会员情况  ：1-已售空 2-已开通会员 3-开通会员 4-可续费

    private Integer buyVipPrice;

    private String name;

    private String address;    //失效日期

    private String rfidNo2;

    private Integer refundType;//用户找零 1原路返回,2返回余额


    // 环信账号信息
    private String easemobUsername;
    private String easemobPassword;
    private String easemobNickname;

    private ReceiptAddr defaultAddr;// 默认的收货地址


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取  levelName
     *
     * @return levelName
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 设置 levelName
     *
     * @param levelName
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * 获取  sexName
     *
     * @return sexName
     */
    public String getSexName() {
        return sexName;
    }

    /**
     * 设置 sexName
     *
     * @param sexName
     */
    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    /**
     * 获取  storeName
     *
     * @return storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置 storeName
     *
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取  authStatusName
     *
     * @return authStatusName
     */
    public String getAuthStatusName() {
        return authStatusName;
    }

    /**
     * 设置 authStatusName
     *
     * @param authStatusName
     */
    public void setAuthStatusName(String authStatusName) {
        this.authStatusName = authStatusName;
    }

    /**
     * 获取  residenceName
     *
     * @return residenceName
     */
    public String getResidenceName() {
        return residenceName;
    }

    /**
     * 设置 residenceName
     *
     * @param residenceName
     */
    public void setResidenceName(String residenceName) {
        this.residenceName = residenceName;
    }

    /**
     * 获取  cityName
     *
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置 cityName
     *
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取easemobUsername
     *
     * @return the easemobUsername
     */
    public String getEasemobUsername() {
        return easemobUsername;
    }

    /**
     * 设置easemobUsername
     *
     * @param easemobUsername the easemobUsername to set
     */
    public void setEasemobUsername(String easemobUsername) {
        this.easemobUsername = easemobUsername;
    }

    /**
     * 获取easemobPassword
     *
     * @return the easemobPassword
     */
    public String getEasemobPassword() {
        return easemobPassword;
    }

    /**
     * 设置easemobPassword
     *
     * @param easemobPassword the easemobPassword to set
     */
    public void setEasemobPassword(String easemobPassword) {
        this.easemobPassword = easemobPassword;
    }

    /**
     * 获取easemobNickname
     *
     * @return the easemobNickname
     */
    public String getEasemobNickname() {
        return easemobNickname;
    }

    /**
     * 设置easemobNickname
     *
     * @param easemobNickname the easemobNickname to set
     */
    public void setEasemobNickname(String easemobNickname) {
        this.easemobNickname = easemobNickname;
    }

    /**
     * 获取  defaultAddr
     *
     * @return defaultAddr
     */
    public ReceiptAddr getDefaultAddr() {
        return defaultAddr;
    }

    /**
     * 设置 defaultAddr
     *
     * @param defaultAddr
     */
    public void setDefaultAddr(ReceiptAddr defaultAddr) {
        this.defaultAddr = defaultAddr;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(Integer vipStatus) {
        this.vipStatus = vipStatus;
    }

    public Float getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Float vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getBuyVipStatus() {
        return buyVipStatus;
    }

    public void setBuyVipStatus(Integer buyVipStatus) {
        this.buyVipStatus = buyVipStatus;
    }

    public Integer getBuyVipPrice() {
        return buyVipPrice;
    }

    public void setBuyVipPrice(Integer buyVipPrice) {
        this.buyVipPrice = buyVipPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRfidNo2() {
        return rfidNo2;
    }

    public void setRfidNo2(String rfidNo2) {
        this.rfidNo2 = rfidNo2;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }
}
