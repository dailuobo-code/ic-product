/**
 * Constants.java
 * @author huangwei
 * @since 2016-1-25
 *  描述：
 */
package com.dailuobo.api.domain.soa.user;

/**
 * Constants.java
 * @author huangwei
 * @since 2016-1-25
 *  描述：
 */
public class UserConstants {

	/**
	 * 该手机号码己注册
	 */
	public static final String ERROR_MSG_MOBILE_HAS_BEEN_REGISTERED = "该手机号码己注册";
	
	public static final String APP_USER_COLLECTION_KEY = "appusercollection";
	
	//商品状态
	public static final Integer PRODUCT_STATUS_OFF_SHELVES = 0;// 下架
	
	public static final Integer PRODUCT_STATUS_ON_SHELVES = 1;// 上架

	public static final Integer PRODUCT_STATUS_OFF_OUT_OF_STOCK = 2;// 无货
	
	//消息类型 1系统消息，2我的资产.
    public static final int PUSH_MOULD_TYPE_1 = 1;
    public static final int PUSH_MOULD_TYPE_2 = 2;

    //子消息类型（系统消息：1取货，2订单，3群发；我的资产：1客服充值，2优惠券 3门店充值, 10我的优惠券列表 ）
    public static final int PUSH_MOULD_MSGTYPE_1 = 1;
    public static final int PUSH_MOULD_MSGTYPE_2 = 2;
    public static final int PUSH_MOULD_MSGTYPE_3 = 3;
	public static final int PUSH_MOULD_MSGTYPE_10 = 10;
    
    public static final String SYS_POINT_LIMIT = "sys_point_limint" ;
    

    public static final int SYS_VIP_STATUS_1 = 1 ;
    public static final int SYS_VIP_STATUS_2 = 2 ;
}
