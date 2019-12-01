package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: gaodong
 * @Date: 2018/10/12 09:09
 * @Description:萝卜粉返回实体
 */
public class GroupLuoBoFanDto implements Serializable {
    private static final long serialVersionUID = -3678593532972743400L;

    //用户图像
    private  String profileImageUrl;
    //用户昵称
    private String wxNickname;
    //贡献金额
    private BigDecimal totalPrice;

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
