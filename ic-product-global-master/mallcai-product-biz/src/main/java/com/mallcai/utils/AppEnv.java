package com.mallcai.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AppEnv {

    @Value("${Service.CityId}")
    public String cityId;

    public boolean isCity(){
        return !"-1".equalsIgnoreCase(cityId);
    }

}
