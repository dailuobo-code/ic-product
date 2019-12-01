/**
 * NickNameGenerator.java
 * @author huangwei
 * @since 2016-1-25
 *  描述：
 */
package com.dailuobo.api.domain.soa.user;

import com.dailuobo.api.domain.util.DatetimeUtil;

import java.util.Random;


/**
 * NickNameGenerator.java
 * 
 * @author huangwei
 * @since 2016-1-25 描述： 用户昵称生成器
 */
public class NickNameGenerator {

	/**
	 * 生成随机昵称
	 * @return
	 */
	public static String generateNickName() {
		String str = DatetimeUtil.getCurrTimeStr();
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		return rannum + str;// 当前时间
	}
}
