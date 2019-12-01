package com.dailuobo.api.domain.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

	public static String getRandomString(int length) {

		String str = "ABCDEFGHGKMNPQRSTUVWXYZ23456789";

		Random random = new Random();

		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(31);// 0~30
			sf.append(str.charAt(number));
		}

		return sf.toString();
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
	
	public static String getOrderNo() {
		String orderNo = String.valueOf(System.currentTimeMillis());
		orderNo = orderNo + ThreadLocalRandom.current().nextInt(9999, 100000);
		orderNo = orderNo.substring(0, 18);
		return orderNo;
	}

}
