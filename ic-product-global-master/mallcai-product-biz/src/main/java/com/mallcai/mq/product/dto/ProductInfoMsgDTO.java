package com.mallcai.mq.product.dto;

import com.mallcai.mq.product.enums.ProductInfoMsgEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Benson
 * @projectName app-admin
 * @packageName com.mallcai.bs.mq.product.dto
 * @description mq商品基础信息
 * @date 2019-06-16 11:10
 */
@Data
@Slf4j

public class ProductInfoMsgDTO implements Serializable {
	
	private static final long serialVersionUID = 1791219212502187620L;
	private Integer hqProductId;
	
	private String l1L2Names;
	
	
	/**
	 * 商品消息业务标识
	 */
	private ProductInfoMsgEnum infoMsgEnum;
	
	
	private Integer c2Old;
	
	
	private String cityFlg;
	private String barCode;
	private Integer keepType;
	private Integer qualityTime;
	
	
	public ProductInfoMsgDTO(Integer hqProductId, String l1L2Names, Integer c2Old, ProductInfoMsgEnum infoMsgEnum,String cityFlg) {
		this.hqProductId = hqProductId;
		this.l1L2Names = l1L2Names;
		this.infoMsgEnum = infoMsgEnum;
		this.c2Old = c2Old;
		this.cityFlg = cityFlg;
	}
	
	
	public ProductInfoMsgDTO(ProductInfoMsgEnum infoMsgEnum, String cityFlg) {
		this.infoMsgEnum = infoMsgEnum;
		this.cityFlg = cityFlg;
	}
	
	public ProductInfoMsgDTO() {
	}
}
