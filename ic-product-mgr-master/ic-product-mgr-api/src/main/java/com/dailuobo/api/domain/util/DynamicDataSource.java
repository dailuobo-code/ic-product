package com.dailuobo.api.domain.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = BindingDataSourceKey.getDataSourceToken();
        // 获取完后解除绑定，防止其他的service方法调用该数据源
        BindingDataSourceKey.clearDataSourceToken();
        return dataSource;
    }
}
