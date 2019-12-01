package com.dailuobo.biz.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppEnv {

    @Value("${Service.CityId}")
    public String cityId;

    public boolean isCity(){
        return !"-1".equalsIgnoreCase(cityId);
    }

}
