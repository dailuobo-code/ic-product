package com.dailuobo.api.domain.util;

public class BindingDataSourceKey {
    private static final ThreadLocal<String> THREAD_LOCAL_TOKEN = new ThreadLocal<String>();

    public static String getDataSourceToken() {
        return THREAD_LOCAL_TOKEN.get();
    }

    public static void setDataSourceToken(String dataSource) {
        THREAD_LOCAL_TOKEN.set(dataSource);
    }

    public static void clearDataSourceToken() {
        THREAD_LOCAL_TOKEN.remove();
    }
}
