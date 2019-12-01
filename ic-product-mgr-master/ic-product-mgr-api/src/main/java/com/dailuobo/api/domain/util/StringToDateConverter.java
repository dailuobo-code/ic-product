package com.dailuobo.api.domain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cowboy on 5/3/2016.
 */
public class StringToDateConverter implements Converter<String, Date> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Date convert(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        SimpleDateFormat sdf;
        if (s.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$")) {
            sdf = new SimpleDateFormat(Constant.DATE_TIME_PATTERN);
        } else if (s.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            sdf = new SimpleDateFormat(Constant.DATE_PATTERN);
        } else {
            return null;
        }
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
