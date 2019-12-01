package com.dailuobo.api.domain.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


/**
 * UnderlineCamelUtils<br/>
 * 描述:
 * 
 * @author huangwei
 * @since 2016-1-20<br/>
 */
public class UnderlineCamelUtils {

	/**
	 * 下划线
	 */
	public static final char UNDERLINE = '_';
	
	
	/** key为下划线方式, value为驼峰标识方式，缓存 */
	private static Map<String, String> underline2CamelMap = new HashMap<String, String>();
	
	/** key为驼峰标识方式, value为下划线方式，缓存 */
	private static Map<String, String> camel2UnderlineMap = new HashMap<String, String>();

	/**
	 * 驼峰转下划线
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		if(camel2UnderlineMap.get(param) == null){
			int len = param.length();
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++) {
				char c = param.charAt(i);
				if (Character.isUpperCase(c)) {
					sb.append(UNDERLINE);
					sb.append(Character.toLowerCase(c));
				} else {
					sb.append(c);
				}
			}
			camel2UnderlineMap.put(param, sb.toString());
		}
		return camel2UnderlineMap.get(param);
	}

	/**
	 * 下划线转驼峰
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		if(StringUtils.isEmpty(underline2CamelMap.get(param))){
			int len = param.length();
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++) {
				char c = param.charAt(i);
				if (c == UNDERLINE) {
					if (++i < len) {
						sb.append(Character.toUpperCase(param.charAt(i)));
					}
				} else {
					sb.append(c);
				}
			}
			underline2CamelMap.put(param, sb.toString());
		}
		return underline2CamelMap.get(param);
	}

}
