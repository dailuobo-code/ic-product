package com.dailuobo.itemcenter.service.test;

import com.dailuobo.itemcenter.api.service.product.ItemCenterRouterService;
import com.mallcai.backend.common.api.Response;
import com.mallcai.service.request.ProductGoodsRelQueryRequest;
import com.mallcai.service.vo.ProductGoodsRelVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lgh
 * @date 2019/10/12
 */
public class ProductGoodsRelServiceTest extends BaseTest{

    @Autowired
    private ItemCenterRouterService itemCenterRouterService;


    /**
     *
     */
    @Test
    public void testProductGoodsRelService(){

        Map<Integer, ProductGoodsRelQueryRequest> requestMap = new HashMap<>();
        ProductGoodsRelQueryRequest request = new ProductGoodsRelQueryRequest();
        request.setCityId(30);
        List<Integer> cityProductList = new ArrayList<>();
        cityProductList.add(10871);
        cityProductList.add(11);
        request.setCityProductIds(cityProductList);

        requestMap.put(30,request);

    }

}
