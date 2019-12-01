/**
 * SpringUtils.java
 * @author huangwei
 * @since 2016-1-28
 *  描述：
 */
package com.dailuobo.api.domain.soa.user;

import org.springframework.context.ApplicationContext;

// TODO: Auto-generated Javadoc
/**
 * SpringUtils.java
 * @author huangwei
 * @since 2016-1-28
 *  描述：
 */
public class SpringUtils {

	/** spring 的ac. */
	private static ApplicationContext ac;
	
	/**
	 * 设置spring的applicationContext.
	 *
	 * @param ac the new appcation context
	 */
	public static void setAppcationContext(ApplicationContext ac){
		SpringUtils.ac = ac;
	}
	
	/**
	 * 获取bean.
	 *
	 * @param <T> the generic type
	 * @param classes the classes
	 * @return the bean
	 */
	public static <T> T getBean(Class<T> classes){
		return ac.getBean(classes);
	}
}
