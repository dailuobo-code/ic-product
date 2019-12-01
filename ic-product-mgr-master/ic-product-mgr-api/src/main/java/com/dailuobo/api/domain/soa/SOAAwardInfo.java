package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * @author wanghb@mallcai.com （汪海波）
 * @date 2018/7/13 15:54
 * <p>
 * 奖品信息
 */
public class SOAAwardInfo implements Serializable {
    private static final long serialVersionUID = 7766303250680232402L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品价值
     */
    private Double awardPrice;

    /**
     * 奖品数量
     */
    private Integer awardNum;

    /**
     * 奖品类型：1-积分，2-实物，3-碎片
     */
    private Integer awardType;

    /**
     * icon地址
     */
    private String awardIcon;

    /**
     * 奖品序号
     */
    private Integer orderNum;

    /**
     * 碎片奖品可合成实物奖品的id
     */
    private Integer parentId;

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
     * icon地址
     *
     * @return award_icon icon地址
     */
    public String getAwardIcon() {
        return awardIcon;
    }

    /**
     * icon地址
     *
     * @param awardIcon icon地址
     */
    public void setAwardIcon(String awardIcon) {
        this.awardIcon = awardIcon == null ? null : awardIcon.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 碎片奖品可合成实物奖品的id
     *
     * @return parent_id 碎片奖品可合成实物奖品的id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 碎片奖品可合成实物奖品的id
     *
     * @param parentId 碎片奖品可合成实物奖品的id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

}