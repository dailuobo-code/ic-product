package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAHqProduct implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -1837608443138909987L;
	
	
/*	 `hq_product_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '总部商品ID',
	  `classify_id` int(10) unsigned NOT NULL COMMENT '二级分类ID',
	  `product_no` varchar(20) DEFAULT NULL COMMENT '商品编号',
	  `hq_product_name` varchar(100) NOT NULL COMMENT '总部商品名称',
	  `remark` varchar(4000) DEFAULT NULL COMMENT '描述',
	  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '总部状态：0，下架；1，上架',
	  `delivery_mode` tinyint(4) NOT NULL DEFAULT '1' COMMENT '配送方式：1，门店自提；2，送货上门',
	  `hq_product_icon` varchar(1000) DEFAULT NULL COMMENT '总部商品ICON',
	  `hq_product_pic` varchar(1000) DEFAULT NULL COMMENT '总部商品图片',
	  `localize_flag` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '本市化：0，否；1，是',
	  `del_flag` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '删除标志：0，删除；1，正常',
	  `create_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
	  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	  `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
	  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	  `bar_code` varchar(50) DEFAULT NULL COMMENT '商品自身条形码',
	  `keep_type` tinyint(2) DEFAULT NULL COMMENT '储存方式 1-常温 2-冷藏 3-冷冻',*/
	
	
	private Integer hqProductId;
	private Integer classifyId;
	private String productNo;
	private String hqProductName;
	private String remark;
	private Integer status;
	private Integer deliveryMode;
	private String hqProductIcon;
	private String hqProductPic;
	private Integer localizeFlag;
	private Integer delFlag;
	private Integer createUserId;
	private String createTime;
	private Integer updateUserId;
	private String updateTime;
	private String barCode;
	private Integer keepType;
	
	
	
	public Integer getHqProductId() {
		return hqProductId;
	}
	public void setHqProductId(Integer hqProductId) {
		this.hqProductId = hqProductId;
	}
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getHqProductName() {
		return hqProductName;
	}
	public void setHqProductName(String hqProductName) {
		this.hqProductName = hqProductName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public String getHqProductIcon() {
		return hqProductIcon;
	}
	public void setHqProductIcon(String hqProductIcon) {
		this.hqProductIcon = hqProductIcon;
	}
	public String getHqProductPic() {
		return hqProductPic;
	}
	public void setHqProductPic(String hqProductPic) {
		this.hqProductPic = hqProductPic;
	}
	public Integer getLocalizeFlag() {
		return localizeFlag;
	}
	public void setLocalizeFlag(Integer localizeFlag) {
		this.localizeFlag = localizeFlag;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public Integer getKeepType() {
		return keepType;
	}
	public void setKeepType(Integer keepType) {
		this.keepType = keepType;
	}
	
	
}
