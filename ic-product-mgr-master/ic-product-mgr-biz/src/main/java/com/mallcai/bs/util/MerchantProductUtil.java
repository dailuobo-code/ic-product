package com.mallcai.bs.util;

import com.dailuobo.api.domain.entity.CityProduct;

/**
 * @author lgh
 * @date 2019/10/16
 */
public class MerchantProductUtil {


    /**
     * 判断是否三方商家商品
     *
     * @param cityProduct
     * @return
     */
    public static boolean isMerchantProduct(CityProduct cityProduct){

        if(null != cityProduct.getMerchantId() && cityProduct.getMerchantId() > 0){
            return true;
        }

        return false;
    }

}
