package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * @author wanghb@mallcai.com （汪海波）
 * @date 2018/7/13 15:54
 * <p>
 * 抽奖活动相关信息
 */
public class SOAAwardAction implements Serializable {

    private static final long serialVersionUID = -5299096277112455992L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 抽奖活动主题
     */
    private String actionName;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束状态：0-未结束，1-已结束
     */
    private String status;

    // /**
    //  * 中奖达标金额
    //  */
    // private Integer awardAmount;

    // /**
    //  * 当前奖池金额
    //  */
    // private Integer poolAmount;

    /**
     * 中奖号码
     */
    private String awardCode;

    /**
     * 奖池金额状态：0-0%，1-10%，2-40%，3-70%，4-100%
     */
    private Integer stage;

    /**
     * 顺序号
     */
    private Integer orderNum;

    /**
     * 主键
     *
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 抽奖活动主题
     *
     * @return action_name 抽奖活动主题
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * 抽奖活动主题
     *
     * @param actionName 抽奖活动主题
     */
    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    /**
     * 开始时间
     *
     * @return begin_time 开始时间
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     * 开始时间
     *
     * @param beginTime 开始时间
     */
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    /**
     * 结束状态：0-未结束，1-已结束
     *
     * @return status 结束状态：0-未结束，1-已结束
     */
    public String getStatus() {
        return status;
    }

    /**
     * 结束状态：0-未结束，1-已结束
     *
     * @param status 结束状态：0-未结束，1-已结束
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    // /**
    //  * 中奖达标金额
    //  *
    //  * @return award_amount 中奖达标金额
    //  */
    // public Integer getAwardAmount() {
    //     return awardAmount;
    // }
    //
    // /**
    //  * 中奖达标金额
    //  *
    //  * @param awardAmount 中奖达标金额
    //  */
    // public void setAwardAmount(Integer awardAmount) {
    //     this.awardAmount = awardAmount;
    // }
    //
    // /**
    //  * 当前奖池金额
    //  *
    //  * @return pool_amount 当前奖池金额
    //  */
    // public Integer getPoolAmount() {
    //     return poolAmount;
    // }
    //
    // /**
    //  * 当前奖池金额
    //  *
    //  * @param poolAmount 当前奖池金额
    //  */
    // public void setPoolAmount(Integer poolAmount) {
    //     this.poolAmount = poolAmount;
    // }

    /**
     * 中奖号码
     *
     * @return award_code 中奖号码
     */
    public String getAwardCode() {
        return awardCode;
    }

    /**
     * 中奖号码
     *
     * @param awardCode 中奖号码
     */
    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode == null ? null : awardCode.trim();
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}