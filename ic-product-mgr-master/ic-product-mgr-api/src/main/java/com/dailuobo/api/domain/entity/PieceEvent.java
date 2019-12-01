/*********************************************************
 *   Copyright (C), 2017-2019, 安徽菜菜电子商务有限公司
 ************************************************************/
package com.dailuobo.api.domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @author liuwei
 * @version 1.0
 * @CreateDate 2019/1/4 17:00
 */

public class PieceEvent  extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id ;
    /**
     * 活动名称
     * */
    private String eventName;
    /**
     * 城市id
     * */
    private Integer cityId;
    /**
     * 萝卜拼类型
     * */
    private Integer pieceType;
    /**
     * 活动开始时间
     * */
    private String pieceStartTime;

    /**
     * 活动结束时间
     * */
    private String pieceEndTime;
    /**
     * 取货日期
     * */
    private String pickupTime;
    /**
     * 活动状态
     * */
    private Integer status;

    private List<Integer> pieceIds;

    public List<Integer> getPieceIds() {
        return pieceIds;
    }

    public void setPieceIds(List<Integer> pieceIds) {
        this.pieceIds = pieceIds;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getPieceType() {
        return pieceType;
    }

    public void setPieceType(Integer pieceType) {
        this.pieceType = pieceType;
    }

    public String getPieceStartTime() {
        return pieceStartTime;
    }

    public void setPieceStartTime(String pieceStartTime) {
        this.pieceStartTime = pieceStartTime;
    }

    public String getPieceEndTime() {
        return pieceEndTime;
    }

    public void setPieceEndTime(String pieceEndTime) {
        this.pieceEndTime = pieceEndTime;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
