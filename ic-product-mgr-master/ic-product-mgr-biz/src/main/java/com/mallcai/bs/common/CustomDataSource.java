package com.mallcai.bs.common;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomDataSource {
    String value();

    String CAICAI = "caicai";

    String GLOBAL = "global";
}
