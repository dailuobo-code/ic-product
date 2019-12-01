package com.dailuobo.api.domain.soa.code;


import java.io.Serializable;

/**
 * Created by ylz on 2018-05-14.
 */
public class CodeConstant implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	   public static final int CARD_STATUS_2 = 2; //充值卡状态  2-待充值
	   
	   public static final int CARD_ERROR_TIME = 86400; //出错失效时长 24小时
	   
		public final static int ORDER_STATUS_2 = 2;  //订单状态：2-支付完成 待取货
		public final static int ORDER_STATUS_4 = 4;  //订单状态：取货完成
		public final static String PAY_STATUS_2 = 	"2"; //支付状态：2-已完成

}
