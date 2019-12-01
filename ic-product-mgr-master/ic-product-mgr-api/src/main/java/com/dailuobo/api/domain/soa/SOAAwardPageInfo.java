package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanghb@mallcai.com （汪海波）
 * @date 2018/7/13 15:54
 * <p>
 * 抽奖页面信息
 */
public class SOAAwardPageInfo implements Serializable {


    private static final long serialVersionUID = -8429739285701656788L;
    /**
     * 所有正在进行中的活动
     */
    private List<SOAAwardAction> awardActions;

    /**
     * 所有活动最新中奖记录
     */
    private List<SOAAwardUser> awardUsers;

    /**
     * 个人中奖奖品
     */
    private List<SOAAward> awards;

    /**
     * 抽奖用户信息
     */
    private SOAAppUser user;

    /**
     * 用户收货地址信息
     */
    private SOAAddress address;

    public List<SOAAwardAction> getAwardActions() {
        return awardActions;
    }

    public void setAwardActions(List<SOAAwardAction> awardActions) {
        this.awardActions = awardActions;
    }

    public List<SOAAwardUser> getAwardUsers() {
        return awardUsers;
    }

    public void setAwardUsers(List<SOAAwardUser> awardUsers) {
        this.awardUsers = awardUsers;
    }

    public List<SOAAward> getAwards() {
        return awards;
    }

    public void setAwards(List<SOAAward> awards) {
        this.awards = awards;
    }

    public SOAAppUser getUser() {
        return user;
    }

    public void setUser(SOAAppUser user) {
        this.user = user;
    }

    public SOAAddress getAddress() {
        return address;
    }

    public void setAddress(SOAAddress address) {
        this.address = address;
    }
}
