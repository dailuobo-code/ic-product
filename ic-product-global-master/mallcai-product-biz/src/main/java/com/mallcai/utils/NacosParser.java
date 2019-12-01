package com.mallcai.utils;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NacosParser {
    @ConfigValue(key = "/app-ic/app-product/syncErpSwitch")
    private JSONObject syncErpSwitch;


    public Boolean isOpenErp2caicai(){
        return Optional.ofNullable(syncErpSwitch).map(e->e.getBoolean("erp2caicai")).orElse(false);
    }


    public Boolean isOpenCaicai2erp(){
        return Optional.ofNullable(syncErpSwitch).map(e->e.getBoolean("caicai2erp")).orElse(false);
    }
}
