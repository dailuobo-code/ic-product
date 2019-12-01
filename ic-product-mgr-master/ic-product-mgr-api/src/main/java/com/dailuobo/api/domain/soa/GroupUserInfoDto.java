package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dailuobo
 * @Auther: gaodong
 * @Date: 2018/10/11 16:47
 * @Description:参团的团员信息
 */
public class GroupUserInfoDto implements Serializable {
    private static final long serialVersionUID = -3678593532972743400L;

    //用户id
    private Integer userId;
    //用户图像
    private  String profileImageUrl;
    //用户昵称
    private String wxNickname;
    //参团的数量
    private Integer productNum;
    //参团的时间
    private Date groupTime;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Date getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Date groupTime) {
        this.groupTime = groupTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
