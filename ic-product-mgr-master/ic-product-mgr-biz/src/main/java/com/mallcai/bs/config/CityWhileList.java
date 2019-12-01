package com.mallcai.bs.config;

import lombok.Data;

import java.util.Date;

/**
 * @author lgh
 * @date 2019/10/16
 */
@Data
public class CityWhileList {

    /**
     * 城市id
     */
    Integer cityId;

    /**
     * 生效时间
     */
    Date effectTime;

}
