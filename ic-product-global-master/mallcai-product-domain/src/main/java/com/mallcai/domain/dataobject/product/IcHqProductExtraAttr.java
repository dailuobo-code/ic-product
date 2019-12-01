package com.mallcai.domain.dataobject.product;

import java.util.Date;

public class IcHqProductExtraAttr {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.hq_product_id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private Integer hqProductId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.attr_type
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private String attrType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.attr_name
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private String attrName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.attr_value
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private String attrValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.attr_desc
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private String attrDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.create_user
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.create_time
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.update_user_id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private Integer updateUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ic_hq_product_extra_attr.update_time
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.id
     *
     * @return the value of ic_hq_product_extra_attr.id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.id
     *
     * @param id the value for ic_hq_product_extra_attr.id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.hq_product_id
     *
     * @return the value of ic_hq_product_extra_attr.hq_product_id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public Integer getHqProductId() {
        return hqProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.hq_product_id
     *
     * @param hqProductId the value for ic_hq_product_extra_attr.hq_product_id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setHqProductId(Integer hqProductId) {
        this.hqProductId = hqProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.attr_type
     *
     * @return the value of ic_hq_product_extra_attr.attr_type
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.attr_type
     *
     * @param attrType the value for ic_hq_product_extra_attr.attr_type
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType == null ? null : attrType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.attr_name
     *
     * @return the value of ic_hq_product_extra_attr.attr_name
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.attr_name
     *
     * @param attrName the value for ic_hq_product_extra_attr.attr_name
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.attr_value
     *
     * @return the value of ic_hq_product_extra_attr.attr_value
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.attr_value
     *
     * @param attrValue the value for ic_hq_product_extra_attr.attr_value
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.attr_desc
     *
     * @return the value of ic_hq_product_extra_attr.attr_desc
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public String getAttrDesc() {
        return attrDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.attr_desc
     *
     * @param attrDesc the value for ic_hq_product_extra_attr.attr_desc
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc == null ? null : attrDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.create_user
     *
     * @return the value of ic_hq_product_extra_attr.create_user
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.create_user
     *
     * @param createUser the value for ic_hq_product_extra_attr.create_user
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.create_time
     *
     * @return the value of ic_hq_product_extra_attr.create_time
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.create_time
     *
     * @param createTime the value for ic_hq_product_extra_attr.create_time
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.update_user_id
     *
     * @return the value of ic_hq_product_extra_attr.update_user_id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.update_user_id
     *
     * @param updateUserId the value for ic_hq_product_extra_attr.update_user_id
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ic_hq_product_extra_attr.update_time
     *
     * @return the value of ic_hq_product_extra_attr.update_time
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ic_hq_product_extra_attr.update_time
     *
     * @param updateTime the value for ic_hq_product_extra_attr.update_time
     *
     * @mbg.generated Mon Oct 14 11:33:55 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}