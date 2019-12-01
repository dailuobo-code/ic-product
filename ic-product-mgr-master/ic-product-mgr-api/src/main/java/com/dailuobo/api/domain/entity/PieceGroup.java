/*********************************************************
 *   Copyright (C), 2017-2019, 安徽菜菜电子商务有限公司
 ************************************************************/
package com.dailuobo.api.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description : 萝卜拼团
 *
 * @author liuwei
 * @version 1.0
 * @CreateDate 2019/1/3 17:03
 */

public class PieceGroup extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 团的id
     * */
    private Integer id;
    /**
     * 拼团商品id
     * */
    private Integer pieceProductId;
    /*
    * 标题
    * */
    private String pieceTitle;
    /**
    * 城市id
    * */
    private Integer cityId;
    /**
     * 商品id
     * */
    private Integer cityProductId;
    /**
     * 萝卜拼类型
     * */
    private Integer pieceType;
    /**
     * 拼团所需人数
     * */
    private Integer peopleNum;
    /**
     * 单独购价格
     * */
    private BigDecimal alonePrice;
    /**
     *拼团开始时间
     * */
    private String pieceStartTime;
    /**
     *拼团结束时间
     * */
    private String pieceEndTime;
    /**
     * 取货时间
     * */
    private String pickupTime;
    /**
     * 状态
     * */
    private Integer status;
    /**
     * 商品图片
     * */
    private String productPic;
    /**
     * 初始值
     * */
    private Integer initialNum;
    /**
     * 是否可开拼
     * */
    private Integer canPiece;
    /**
    *是否置顶
    * */
    private Integer stick;
    /**
     * 置顶时间
     * */
    private String stickTime;

    /**
     * 活动id
     * */
    private Integer eventId;

    /**
     * 可用量
     * */
    private Integer available;

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPieceProductId() {
        return pieceProductId;
    }

    public void setPieceProductId(Integer pieceProductId) {
        this.pieceProductId = pieceProductId;
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

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public Integer getInitialNum() {
        return initialNum;
    }

    public void setInitialNum(Integer initialNum) {
        this.initialNum = initialNum;
    }

    public Integer getCanPiece() {
        return canPiece;
    }

    public void setCanPiece(Integer canPiece) {
        this.canPiece = canPiece;
    }

    public Integer getStick() {
        return stick;
    }

    public void setStick(Integer stick) {
        this.stick = stick;
    }

    public String getStickTime() {
        return stickTime;
    }

    public void setStickTime(String stickTime) {
        this.stickTime = stickTime;
    }
}
