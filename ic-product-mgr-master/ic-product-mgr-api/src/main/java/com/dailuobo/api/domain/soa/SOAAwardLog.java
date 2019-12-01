package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * @author wanghb@mallcai.com （汪海波）
 * @date 2018/7/13 15:54
 * <p>
 * 用户抽奖日志
 */
public class SOAAwardLog implements Serializable {

    private static final long serialVersionUID = 7941510340649224637L;

    //聚宝盆抽奖，本次抽奖啥也得不到，记录消耗的积分或者余额

    /**
     * 抽奖消耗类型
     */
    private String costType;

    /**
     * 抽奖消耗的余额或积分
     */
    private Integer cost;

    ////////////////////////////////////////////////////////////////////

    //轮盘抽奖，本次抽奖必得奖品，记录中奖信息
    /**
     * 抽奖所得奖品类型
     */
    private Integer awardType;

    /**
     * 抽奖所得奖品名称
     */
    private String awardName;

    /**
     * 抽奖所得奖品id
     */
    private Integer awardId;

    ////////////////////////////////////////////////////////////////////

    //一元购抽奖，本次抽奖只能得到一个奖号，记录奖号
    /**
     * 抽奖所得号码
     */
    private String awardCode;

    ////////////////////////////////////////////////////////////////////

    /**
     * 抽奖时间
     */
    private String createTime;

    /**
     * 抽奖号码
     *
     * @return award_code 抽奖号码
     */
    public String getAwardCode() {
        return awardCode;
    }

    /**
     * 抽奖号码
     *
     * @param awardCode 抽奖号码
     */
    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode == null ? null : awardCode.trim();
    }

    /**
     * 抽奖时间
     *
     * @return create_time 抽奖时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 抽奖时间
     *
     * @param createTime 抽奖时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }
}