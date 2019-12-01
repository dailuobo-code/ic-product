/**
 * BeanMapStandardUtils.java
 * @author huangwei
 * @since 2016-1-23
 *  描述：
 */
package com.dailuobo.api.domain.util;

import org.apache.commons.beanutils.BeanUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * BeanMapStandardUtils.java
 * @author huangwei
 * @since 2016-1-23
 *  描述：
 */
public class BeanMapUtils {
	
	/** logger */
	private static Logger logger = LoggerFactory.getLogger(BeanMapUtils.class);

	
	
	/**
	 * 将document转为bean对象,针对document中的key是驼峰标识
	 * @param docuemnt	将要转化的document
	 * @param classes	需要转化成的对象类型
	 * @return
	 */
	public static <T> T transDocument2BeanWithOutConversionCamel(Document docuemnt, Class<T> classes){
		return transDocument2Bean(docuemnt, classes, Boolean.FALSE);
	}
	
	/**
	 * 将document转为bean对象,针对document中的key是下划线标识
	 * @param docuemnt	将要转化的document
	 * @param classes	需要转化成的对象类型
	 * @return
	 */
	public static <T> T transDocument2BeanWithConversionCamel(Document docuemnt, Class<T> classes){
		return transDocument2Bean(docuemnt, classes, Boolean.TRUE);
	}
	
	/**
	 * 将document转为bean对象
	 * @param docuemnt		将要转化的document
	 * @param classes		需要转化成的对象类型
	 * @param needToCamel	是否需要做驼峰转化[当document中的key为下划线标识时，需要为true]
	 * @return
	 */
	private static <T> T transDocument2Bean(Document docuemnt, Class<T> classes, Boolean needToCamel){
		try {
			T t = classes.newInstance();
			return transDocument2Bean(docuemnt, t, needToCamel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	
	
	/**
	 * 将document转为bean对象
	 * @param document		将要转化的document
	 * @param t				转化后的对象
	 * @param needToCamel	是否需要做驼峰转化[当document中的key为下划线标识时，需要为true]
	 * @return
	 */
	private static <T> T transDocument2Bean(Document document, T t, Boolean needToCamel){
		try {
			Iterator<Map.Entry<String, Object>> iter = document.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry<String, Object> entry = iter.next();
				if(needToCamel){
					BeanUtils.setProperty(t, UnderlineCamelUtils.underlineToCamel(entry.getKey()), entry.getValue());
				}else{
					BeanUtils.setProperty(t, entry.getKey(), entry.getValue());
				}
			}
			return t;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	
	/**
	 * 将bean转为document对象,针对document中的key是下划线标识
	 * @param obj 需要转化的bean
	 */
	public static Document transBean2DocumentWithConversionUnderLine(Object obj){
		return transBean2Document(obj, Boolean.TRUE);
	}
	
	
	/**
	 * 将bean转为document对象,针对document中的key是驼峰标识
	 * @param obj 需要转化的bean
	 */
	public static Document transBean2DocumentWithOutConversionUnderLine(Object obj){
		return transBean2Document(obj, Boolean.FALSE);
	}
	
	
	
	/**
	 * 将obj转为document对象
	 * @param obj				将要转化的对象
	 * @param needToUnderLine	是否需要做驼峰转化[当document中的key为下划线标识时，需要为true]
	 * @return
	 */
	private static Document transBean2Document(Object obj, Boolean needToUnderLine){
		if (obj == null) {
			return null;
		}
		Document document = new Document();
		try {
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(obj);
			for (PropertyDescriptor property : propertyDescriptors){
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if(value != null){
						if(needToUnderLine){
							document.put(UnderlineCamelUtils.camelToUnderline(key), value);
						}else{
							document.put(key, value);
						}
					}
				}
			}
			return document;
		} catch (Exception e) {
			logger.error("transBean2Map Error ", e);
			return null;
		}
	}
	
	
	/**
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(obj);
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			logger.error("transBean2Map Error ", e);
		}
		return map;
	}
	
	
	
	/**
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, String> transBean2MapWithValueStr(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(obj);
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, String.valueOf(value));
				}

			}
		} catch (Exception e) {
			logger.error("transBean2Map Error ", e);
		}
		return map;
	}
	


	/**
	 * Map-->bean的转化
	 * 标准转化
	 * 
	 * @param map
	 *            待转化的map
	 * @param obj
	 *            返回的obj
	 */
	public static void transMap2Bean(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			logger.error("transMap2Bean2 Error ", e);
		}
	}
	
	
	/**
	 * 将source转为target对象,以target中的字段为主
	 * 允许源对象中没有部分字段
	 * 这个主要解决的是有时我们需要多个bean用于不同的传输，只是有的多几个字段，有的少几个字段
	 * @param source	源对象
	 * @param target	目标对象
	 */
	public static void bean2Bean(Object source, Object target)throws Exception{
		for (PropertyDescriptor property : getPropertyDescriptors(target)) {
			String key = property.getName();
			if (!key.equals("class")) {
				try{
					Method getMethod = source.getClass().getDeclaredMethod(property.getReadMethod().getName());
					if(getMethod != null){
						Object value = getMethod.invoke(source);
						BeanUtils.setProperty(target, key, value);
					}
				}catch(NoSuchMethodException e){
//					StringBuilder msg = new StringBuilder();
//					msg.append(source.getClass().getName()).append("转化为:")
//					   .append(target.getClass().getName()).append("时")
//					   .append(key).append(" 未找到对应的get方法。");
//					logger.warn(msg.toString(), e);
					continue;
				}
			}
		}
	}
	
	
	
	/**
	 * 获取对象的property数据
	 * @param object 对象 
	 * @return
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(Object object){
		BeanInfo targetBeanInfo;
		try {
			targetBeanInfo = Introspector.getBeanInfo(object.getClass());
			return targetBeanInfo.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		
	}

	/**
	 * 将一组数据平均分成n组
	 *
	 * @param source 要分组的数据源
	 * @param n      平均分成n组
	 *param <T>
	 * @return
	 */
	public static <T> List<List<T>>  averageAssign(List<T> source, int n) {
		List<List<T>> result = new ArrayList<List<T>>();
		int remainder = source.size() % n;  //(先计算出余数)
		int number = source.size() / n;  //然后是商
		int offset = 0;//偏移量
		for (int i = 0; i < n; i++) {
			List<T> value = null;
			if (remainder > 0) {
				value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
				remainder--;
				offset++;
			} else {
				value = source.subList(i * number + offset, (i + 1) * number + offset);
			}

			result.add(value);
		}
		return result;
	}

	/**
	 * 将一组数据固定分组，每组n个元素
	 * @param source 要分组的数据源
	 * @param n      每组n个元素
	 * @param <T>
	 * @return
	 */
	public static  <T> List<List<T>> fixedGrouping(List<T> source, int n) {
		if (null == source || source.size() == 0 || n <= 0)
			return null;
		List<List<T>> result = new ArrayList<List<T>>();

		int sourceSize = source.size();
		int size = (source.size() / n) + 1;
		for (int i = 0; i < size; i++) {
			List<T> subset = new ArrayList<T>();
			for (int j = i * n; j < (i + 1) * n; j++) {
				if (j < sourceSize) {
					subset.add(source.get(j));
				}
			}
			result.add(subset);
		}
		return result;
	}
}
