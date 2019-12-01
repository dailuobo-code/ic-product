package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * @author wanghb@mallcai.com （汪海波）
 * @date 2018/7/13 15:54
 * <p>
 * 抽奖需要消耗信息
 */
public class SOAAwardCost implements Serializable {

    private static final long serialVersionUID = -4683470904790775200L;
    /**
     * 抽奖次数（多次可优惠）
     */
    private Integer num;

    /**
     * 抽奖消耗
     */
    private Integer cost;

    /**
     * 类型：1-积分，2-余额
     */
    private String type;

    /**
     * 抽奖次数（多次可优惠）
     *
     * @return num 抽奖次数（多次可优惠）
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 抽奖次数（多次可优惠）
     *
     * @param num 抽奖次数（多次可优惠）
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 抽奖消耗
     *
     * @return cost 抽奖消耗
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * 抽奖消耗
     *
     * @param cost 抽奖消耗
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * 类型：1-积分，2-余额
     *
     * @return type 类型：1-积分，2-余额
     */
    public String getType() {
        return type;
    }

    /**
     * 类型：1-积分，2-余额
     *
     * @param type 类型：1-积分，2-余额
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

}