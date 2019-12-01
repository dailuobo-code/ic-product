package com.mallcai.domain.dataobject.product;

import java.io.Serializable;

public class ProductCategoryRelDO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3726350407513413095L;
    private Integer classifyId;
    private String classifyNo;
    private Integer cityId;
    private Integer cityProductId;
    private String createTime;
    private String classifyName;
    private String updateTime;


    private Integer hqProductId;

    public Integer getHqProductId() {
        return hqProductId;
    }

    public void setHqProductId(Integer hqProductId) {
        this.hqProductId = hqProductId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * 获取  classifyId
     *
     * @return classifyId
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * 设置 classifyId
     *
     * @param classifyId
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * 获取  classifyNo
     *
     * @return classifyNo
     */
    public String getClassifyNo() {
        return classifyNo;
    }

    /**
     * 设置 classifyNo
     *
     * @param classifyNo
     */
    public void setClassifyNo(String classifyNo) {
        this.classifyNo = classifyNo;
    }

    /**
     * 获取  cityId
     *
     * @return cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置 cityId
     *
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取  cityProductId
     *
     * @return cityProductId
     */
    public Integer getCityProductId() {
        return cityProductId;
    }

    /**
     * 设置 cityProductId
     *
     * @param cityProductId
     */
    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    /**
     * 获取  createTime
     *
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置 createTime
     *
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  updateTime
     *
     * @return updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 updateTime
     *
     * @param updateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
