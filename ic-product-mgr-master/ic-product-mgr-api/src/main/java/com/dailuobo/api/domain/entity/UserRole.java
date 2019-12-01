package com.dailuobo.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRole implements Serializable{


    private static final long serialVersionUID = -8474500246214076438L;
    /**
     * 编码
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 门店ID
     */
    private Integer storeId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户登陆名称
     */
    private String userName;

    /**
     * 用户真是名称
     */
    private String realName;

    /**
     * 用户角色名称
     */

    private String roleName;

    /**
     * 用户手机号码
     */

    private String phone;

    /**
     * 用户所在门店
     */

    private String storeName;

    /**
     * 用户所在城市
     */

    private String cityNames;

    /**
     * 用户角色类型
     */

    private String roleType;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }



}