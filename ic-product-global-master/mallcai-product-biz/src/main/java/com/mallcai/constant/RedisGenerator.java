package com.mallcai.constant;

public class RedisGenerator {
    public static String generateProdClassifyKey(Integer type,Integer classifyId) {
        if(type==1){
            return "productClassify:type:" + type;
        }
        if(type==2){
            return "productClassify:type:" + type+":classifyId:"+classifyId;
        }
        return null;

    }
}
