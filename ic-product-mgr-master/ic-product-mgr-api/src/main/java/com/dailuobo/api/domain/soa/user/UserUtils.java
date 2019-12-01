package com.dailuobo.api.domain.soa.user;


import com.dailuobo.api.domain.util.DatetimeUtil;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class UserUtils {
	
	
	/**
	 * 获取当前时间 格式 yyyy-MM-dd HH:mm:ss
	 * 如2016-02-17 11:20:33.
	 *
	 * @return the string
	 */
	public static String now() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Gets the some day.
	 *
	 * @param offset the offset
	 * @return the some day
	 */
	public static String getSomeDay(int offset) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, offset);
		return DatetimeUtil.formatDate(today.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * Gets the hour min.
	 * 返回当前时间 精确到分钟 2016-02-19 
	 * @return the hour min
	 */
	public static String getCurrentHourMin() {
		return DatetimeUtil.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm");
	}
	
	
	
	public static String formatFloat(Float fvalue) {
		if(fvalue == null) return "0.00";
		return new DecimalFormat("##0.00").format(fvalue);
	}
	
	 public static int compare_date(String DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	 
	 public static int compare_date2(String DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	/* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    /* 
     * 获取当前时间一定时间后的时间（分钟）
     */ 
    public static String getAfterTime(int min){
    	long curren = System.currentTimeMillis();
        curren += min * 60 * 1000;
        Date da = new Date(curren);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        return dateFormat.format(da);
    }
    
	/**
	 * 获取当前时间 格式 yyyy-MM
	 * 如2018-07
	 *
	 * @return the string
	 */
	public static String getYearMonth() {
		return new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
	}
	/**
	 * 获取当前时间 格式 yyyy-MM-dd 
	 * 如2018-07-18 
	 *
	 * @return the string
	 */
	public static String getYearMonthDay() {
		return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	}
	
	public static String getYesterMonthday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	public static String getLastMonthday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		cal.add(Calendar.YEAR,1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	public static String getBeforeThreeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,-3);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	public static String getDay(String day,Integer count) {  //day - month 的日期
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - count);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }
	
	public static String getLastYearMonthDay(String startDate,Integer count) throws ParseException {  //day的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(startDate);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date); 
		calendar.add(calendar.YEAR, count);//把日期往后增加count年.整数往后推,负数往前移动
		calendar.add(calendar.DATE, -1);//把日期往后增加count年.整数往后推,负数往前移动
		date=calendar.getTime();   
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	public static void main(String[] args) throws ParseException{
		System.out.println(getLastYearMonthDay("2018-08-09",1));
	}
}
