/**
 * Constants.java
 * @author huangwei
 * @since 2016-3-29
 *  描述：
 */
package com.dailuobo.api.domain.soa.city;

import java.io.Serializable;

/**
 * Constants.java
 * @author huangwei
 * @since 2016-3-29
 *  描述：
 */
public class CityConstants implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	//获取商品列表，类型属于
	public static final Integer TYPE_BANNER = 10;	// banner列表
	public static final Integer TYPE_TILE = 20;		// 磁贴列表
	public static final Integer TYPE_HEADLINE = 30;// 呆萝卜头条
	public static final Integer TYPE_PROD_CLASSIFY = 4;// 二级分类商品列表
	public static final Integer TYPE_PROD_THEME = 40;// 主题商品列表
	public static final Integer TYPE_PROD_PRESELL = 50;// 预售
	public static final Integer TYPE_PROD_PIC = 60;// 图片
	public static final Integer TYPE_PROD_ADVERT = 6;// 广告商品列表
	public static final Integer COUPON_PRODUCT_LIST=80;//优惠券跳转商品列表
	
	//商品状态
	public static final Integer PRODUCT_STATUS_OFF_SHELVES = 0;// 下架
	
	public static final Integer PRODUCT_STATUS_ON_SHELVES = 1;// 上架

	public static final Integer PRODUCT_STATUS_OFF_OUT_OF_STOCK = 2;// 无货

}
