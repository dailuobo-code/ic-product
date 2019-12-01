package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2016/12/15.
 */
public class SOAExchPointsRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2020916868546525618L;
	private Integer id;
    private Integer userId;
    private String phone;
    private Integer cEntity;
    private Integer cType;
    private Integer points;
    private Integer prePoints;
    private Integer postPoints;
    private String createTime;
    private String reason;
    private String remark;

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

    public Integer getcEntity() {
        return cEntity;
    }

    public void setcEntity(Integer cEntity) {
        this.cEntity = cEntity;
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPrePoints() {
        return prePoints;
    }

    public void setPrePoints(Integer prePoints) {
        this.prePoints = prePoints;
    }

    public Integer getPostPoints() {
        return postPoints;
    }

    public void setPostPoints(Integer postPoints) {
        this.postPoints = postPoints;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
