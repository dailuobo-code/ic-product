package com.dailuobo.api.domain.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {
	public static String getSomeDay(int offset) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, offset);
		return DatetimeUtil.formatDate(today.getTime(), "yyyy-MM-dd");
	}
	
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
	
	public static String getBeforeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,-1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	public static String getYear() {
		return new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
	}

	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	}

	public static float sub(float t1, float... arr) {
		BigDecimal result = new BigDecimal(Float.toString(t1));
		BigDecimal b2 = null;

		for(float t2 : arr) {
			b2 = new BigDecimal(Float.toString(t2));
			result = result.subtract(b2);
		}

		return result.floatValue();
	}

	/**
	 * Mul.
	 *
	 * @param t1 the t1
	 * @param arr the arr
	 * @return the float
	 */
	public static float mul(float t1, float... arr) {
		BigDecimal result = new BigDecimal(Float.toString(t1));
		BigDecimal b2 = null;

		for(float t2 : arr) {
			b2 = new BigDecimal(Float.toString(t2));
			result = result.multiply(b2);
		}

		return result.floatValue();
	}

	public static Double add(Double t1, Double... arr) {
		BigDecimal result = new BigDecimal(Double.toString(t1));
		BigDecimal b2 = null;

		for(Double t2 : arr) {
			b2 = new BigDecimal(Double.toString(t2));
			result = result.add(b2);
		}

		return result.doubleValue();
	}

	/**
	 *
	 * @param d
	 * @param num 保留小数位数
	 * @param roundingMode 四舍五入等
	 * @return
	 */
	public static Double formatNumber(Double d, int num, RoundingMode roundingMode){
		String s=d.toString();
		BigDecimal bg=new BigDecimal(s).setScale(num,roundingMode);
		return bg.doubleValue();
	}

	public static String getDateFormatStr(Date date ,String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	public static String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	* 数字字符串转Integer数组（逗号分隔）
	*  1，2，3，4，5
	*  {1，2，3，4，5}
	*/
	public static Integer[] getArrays(String numbers){
		String [] result = numbers.split(",");
		Integer[] number = new Integer[result.length];
		for(int i = 0; i < result.length; i++)
		{
			if(result[i]!=null){
			number[i] = Integer.parseInt(result[i]);
			}
		}
		return number;
	}
}
