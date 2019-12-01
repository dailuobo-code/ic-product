package com.dailuobo.service.annotation;

import java.lang.annotation.*;

/**
 * @author wangshifeng
 * @date 2019-08-06 15:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface DubboParameter {

    String version() default "";

    String zookeeper() default "";

    Class<?> interfaceClass();
}
