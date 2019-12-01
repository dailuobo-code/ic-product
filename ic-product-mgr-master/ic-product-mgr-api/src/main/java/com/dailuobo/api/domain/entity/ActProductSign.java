/*********************************************************
 *   Copyright (C), 2017-2018, 安徽菜菜电子商务有限公司
 ************************************************************/
package com.dailuobo.api.domain.entity;

import java.io.Serializable;

/**
 * Description ： 活动商品标记
 *
 * @author liuwei
 * @version 1.0
 * @CreateDate 2018/12/24 11:52
 */

public class ActProductSign extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     * */
    private Integer id;


    /**
     *城市id
     * */
    private  Integer cityId;

    /**
     *商品sku
     * */
    private String productNo;

    /**
     *商品id
     * */
    private Integer cityProductId;

    /**
     *标记类型
     * */
    private String signVal;

    /**
     *记录日期
     * */
    private String signDate;

    /**
     * 门店数组集合
     * */
    private Integer[] storeIds;

    /**
     * 多门店定时任务id
     */
    private Integer multiBargainTaskId;

    /**
     *是否删除 0-删除 1-正常
     * */
    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getSignVal() {
        return signVal;
    }

    public void setSignVal(String signVal) {
        this.signVal = signVal;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer[] getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(Integer[] storeIds) {
        this.storeIds = storeIds;
    }

    public Integer getMultiBargainTaskId() {
        return multiBargainTaskId;
    }

    public void setMultiBargainTaskId(Integer multiBargainTaskId) {
        this.multiBargainTaskId = multiBargainTaskId;
    }
}
