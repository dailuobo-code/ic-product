package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GroupDrawDetail implements Serializable {

    private static final long serialVersionUID = -6878055812185779599L;

    private Long id;

    private Long drawId;

    private Integer cityId;

    private Integer type;

    private Integer status;

    private Integer userId;

    private BigDecimal prePrice;

    private BigDecimal postPrice;

    private BigDecimal drawPrice;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrawId() {
        return drawId;
    }

    public void setDrawId(Long drawId) {
        this.drawId = drawId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(BigDecimal prePrice) {
        this.prePrice = prePrice;
    }

    public BigDecimal getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(BigDecimal postPrice) {
        this.postPrice = postPrice;
    }

    public BigDecimal getDrawPrice() {
        return drawPrice;
    }

    public void setDrawPrice(BigDecimal drawPrice) {
        this.drawPrice = drawPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}