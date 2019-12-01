package com.mallcai.bs.interceptor;

import com.mallcai.bs.common.CustomDataSource;
import com.dailuobo.api.domain.util.BindingDataSourceKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class DataSourceAspect {
    /**
     * 拦截目标方法,通过注解来获取数据源名称key值，设置到ThreadLocal中
     */
    public void intercept(JoinPoint point) throws Exception {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 使用目标注解类型，如果没有，则使用其接口指定类型
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    /**
     * 获取目标对象方法注解和类型注解中的注解
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(CustomDataSource.class)) {
                CustomDataSource cds = clazz.getAnnotation(CustomDataSource.class);
                BindingDataSourceKey.setDataSourceToken(cds.value());
            }
            // 方法注解覆盖，以方法注解为最后值
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(CustomDataSource.class)) {
                CustomDataSource cds = m.getAnnotation(CustomDataSource.class);
                BindingDataSourceKey.setDataSourceToken(cds.value());
            }
        } catch (Exception e) {
            //log.error(clazz + ":" + e.getMessage());
        }
    }
}
