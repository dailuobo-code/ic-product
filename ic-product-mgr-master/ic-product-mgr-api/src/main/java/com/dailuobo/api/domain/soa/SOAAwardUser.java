package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * @author wanghb@mallcai.com （汪海波）
 * @date 2018/7/13 15:54
 * <p>
 * 中奖用户信息
 */
public class SOAAwardUser implements Serializable {
    private static final long serialVersionUID = 2226559171612288048L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 中奖号码
     */
    private String awardCode;

    /**
     * 中奖用户id
     */
    private Integer userId;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 奖品id
     */
    private Integer awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品数量
     */
    private Integer awardNum;

    /**
     * 奖品价值
     */
    private Double awardPrice;

    /**
     * 奖品类型：1-积分，2-实物，3-碎片
     */
    private Integer awardType;

    /**
     * 中奖时间
     */
    private String createTime;

    /**
     * 合成状态：0-未合成，1-已合成
     */
    private Integer composeStatus;

    /**
     * 奖品状态：0-抽奖所得，1-合成所得
     */
    private Integer awardStatus;

    /**
     * 奖品发放状态：1-未发放，2-已发放
     */
    private Integer sendStatus;

    /**
     * 奖品发放时间
     */
    private String sendTime;


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

    /**
     * 中奖用户id
     *
     * @return user_id 中奖用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 中奖用户id
     *
     * @param userId 中奖用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 奖品id
     *
     * @return award_id 奖品id
     */
    public Integer getAwardId() {
        return awardId;
    }

    /**
     * 奖品id
     *
     * @param awardId 奖品id
     */
    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    /**
     * 奖品名称
     *
     * @return award_name 奖品名称
     */
    public String getAwardName() {
        return awardName;
    }

    /**
     * 奖品名称
     *
     * @param awardName 奖品名称
     */
    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
    }

    /**
     * 奖品数量
     *
     * @return award_num 奖品数量
     */
    public Integer getAwardNum() {
        return awardNum;
    }

    /**
     * 奖品数量
     *
     * @param awardNum 奖品数量
     */
    public void setAwardNum(Integer awardNum) {
        this.awardNum = awardNum;
    }

    /**
     * 奖品价值
     *
     * @return award_price 奖品价值
     */
    public Double getAwardPrice() {
        return awardPrice;
    }

    /**
     * 奖品价值
     *
     * @param awardPrice 奖品价值
     */
    public void setAwardPrice(Double awardPrice) {
        this.awardPrice = awardPrice;
    }

    /**
     * 奖品类型：1-积分，2-实物，3-碎片
     *
     * @return award_type 奖品类型：1-积分，2-实物，3-碎片
     */
    public Integer getAwardType() {
        return awardType;
    }

    /**
     * 奖品类型：1-积分，2-实物，3-碎片
     *
     * @param awardType 奖品类型：1-积分，2-实物，3-碎片
     */
    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    /**
     * 中奖时间
     *
     * @return create_time 中奖时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 中奖时间
     *
     * @param createTime 中奖时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 合成状态：0-未合成，1-已合成
     *
     * @return compose_status 合成状态：0-未合成，1-已合成
     */
    public Integer getComposeStatus() {
        return composeStatus;
    }

    /**
     * 合成状态：0-未合成，1-已合成
     *
     * @param composeStatus 合成状态：0-未合成，1-已合成
     */
    public void setComposeStatus(Integer composeStatus) {
        this.composeStatus = composeStatus;
    }

    /**
     * 奖品状态：0-抽奖所得，1-合成所得
     *
     * @return award_status 奖品状态：0-抽奖所得，1-合成所得
     */
    public Integer getAwardStatus() {
        return awardStatus;
    }

    /**
     * 奖品状态：0-抽奖所得，1-合成所得
     *
     * @param awardStatus 奖品状态：0-抽奖所得，1-合成所得
     */
    public void setAwardStatus(Integer awardStatus) {
        this.awardStatus = awardStatus;
    }

    /**
     * 奖品发放状态：1-未发放，2-已发放
     *
     * @return send_status 奖品发放状态：1-未发放，2-已发放
     */
    public Integer getSendStatus() {
        return sendStatus;
    }

    /**
     * 奖品发放状态：1-未发放，2-已发放
     *
     * @param sendStatus 奖品发放状态：1-未发放，2-已发放
     */
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 奖品发放时间
     *
     * @return send_time 奖品发放时间
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * 奖品发放时间
     *
     * @param sendTime 奖品发放时间
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}