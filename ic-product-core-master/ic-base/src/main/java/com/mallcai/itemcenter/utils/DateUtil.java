package com.mallcai.itemcenter.utils;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import java.util.Date;

/**
 * DateUtils
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 15:12<br/>
 */
public class DateUtil {

    private static final DateTimeParser[] parsers = {
            DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
            DateTimeFormat.forPattern("yyyyMMdd").getParser()
    };
    private static final DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter();

    /**
     * 检查输入的日期是否为有效格式
     *
     * @param value 输入的日期
     * @return 是否有效
     */
    public static boolean isValidDate(String value) {
        try {
            dateFormatter.parseDateTime(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取当天的开始时间,即yyyy-mm-dd 00:00:00
     *
     * @param date 当天日期
     * @return 当天的开始时间
     */
    public static Date withTimeAtStartOfDay(Date date) {
        return new DateTime(date).withTimeAtStartOfDay().toDate();
    }

    /**
     * 获取当天的结束时间,即yyyy-mm-dd 23:59:59
     *
     * @param date 当天日期
     * @return 当天的结束时间
     */
    public static Date withTimeAtEndOfDay(Date date) {
        return new DateTime(date).withTimeAtStartOfDay().plusDays(1).minusSeconds(1).toDate();
    }
}
