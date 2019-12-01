package com.dailuobo.api.domain.soa.code;


import com.dailuobo.api.domain.util.DatetimeUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {
	
	/**
	 * Gets the order no.
	 * 获取订单id/编号
	 * Long型日期+5位随机数
	 * 其中日期采用的是System.currentTimeMillis()
	 * @return the order no
	 */
	public static String getOrderNo() {
		String orderNo = String.valueOf(System.currentTimeMillis());
		orderNo = orderNo + ThreadLocalRandom.current().nextInt(9999, 100000);
		orderNo = orderNo.substring(0, 18);
		return orderNo;
	}
	
	public static String getOrderNoV2() {
		try {
			long startTime = new SimpleDateFormat("yyyy-MM-dd").parse("2016-06-18").getTime();
			return String.valueOf(Calendar.getInstance().getTime().getTime() - startTime) 
					+ ThreadLocalRandom.current().nextInt(99, 1000);
		} catch(Exception e) {
			return getOrderNo();
		}
	}
	
	public static String getSomeDay(int offset) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, offset);
		return DatetimeUtil.formatDate(today.getTime(), "yyyy-MM-dd");
	}
	
	public static String now() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	public static float add(float t1, float... arr) {
		BigDecimal result = new BigDecimal(Float.toString(t1));
		BigDecimal b2 = null;
		
		for(float t2 : arr) {
			b2 = new BigDecimal(Float.toString(t2));
			result = result.add(b2);
		}
		return result.floatValue();
	}
	
	public static float mul(float t1, float... arr) {
		BigDecimal result = new BigDecimal(Float.toString(t1));
		BigDecimal b2 = null;
		
		for(float t2 : arr) {
			b2 = new BigDecimal(Float.toString(t2));
			result = result.multiply(b2);
		}
		
		return result.floatValue();
	}
	

}
