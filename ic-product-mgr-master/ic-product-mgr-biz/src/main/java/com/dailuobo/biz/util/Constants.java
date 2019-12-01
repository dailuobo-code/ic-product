package com.dailuobo.biz.util;

/**
 * @author wangshifeng
 * @date 2019-08-09 10:15
 */
public class Constants {


    public static class RedisConstants {

        private static final int SECONDS_OF_ONE_DAYS = 60 * 60 * 24;

        private static final int SECONDS_OF_TWO_DAYS = SECONDS_OF_ONE_DAYS * 2;

        public static final int CITY_FORECAST_KEY_TIME_OUT = SECONDS_OF_ONE_DAYS;

    }

}
