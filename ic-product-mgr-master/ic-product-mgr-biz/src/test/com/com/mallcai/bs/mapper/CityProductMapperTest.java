package com.mallcai.bs.mapper;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.ic.api.enums.ImportBusinessEnum;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductSearchRequest;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.mallcai.bs.dao.CityProductDao;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CityProductMapperTest extends BaseTest {

    @Autowired
    CityProductDao cityProductDao;

    @Test
    public void productListByCity() {
        CityProductQueryRequest request = new CityProductQueryRequest();
        request.setCityStatus(1);
        request.setCityProductIds(Lists.newArrayList(3, 6, 9));
        List<CityProduct> cityProductList = cityProductDao.productListByCity(request);
        Assert.assertNotNull(cityProductList);
        System.out.println(JSON.toJSONString(cityProductList));
        request.setIsNew(1);
        cityProductList = cityProductDao.productListByCity(request);
        Assert.assertTrue(CollectionUtils.isEmpty(cityProductList));
        request.setIsNew(0);
        request.setCityProductIds(null);
        request.setHqProductName("青菜");
        cityProductList = cityProductDao.productListByCity(request);
        Assert.assertTrue(CollectionUtils.isNotEmpty(cityProductList));
        System.out.println(JSON.toJSON(cityProductList));
        request.setIsShare("1");
        cityProductList = cityProductDao.productListByCity(request);
        System.out.println(JSON.toJSON(cityProductList));
        request.setIsShare("0");
        cityProductList = cityProductDao.productListByCity(request);
        System.out.println(JSON.toJSON(cityProductList));
        cityProductList = cityProductDao.productListByCity(request);
        System.out.println(JSON.toJSON(cityProductList));


    }

    @Test
    public void findCityProductByCityId() {
        /*CityProductSearchRequest request = new CityProductSearchRequest();
        request.setCityId(30);
        request.setCityStatus(1);
        request.setStoreId(3);
        PageHelper.startPage(1, 10);
        List<CityProduct> cityProductByCityId = cityProductDao.findCityProductByCityId(request);
        Assert.assertNotNull(cityProductByCityId);
        Assert.assertEquals(cityProductByCityId.size(), 10);
        System.out.println(JSON.toJSONString(cityProductByCityId));

        PageHelper.startPage(1, 10);
        request.setCityId(30);
        request.setCityStatus(1);
        request.setBusinessEnum(ImportBusinessEnum.CHANGE_PRICE_FROM_BATCH_STORE_GROUP);
        cityProductByCityId = cityProductDao.findCityProductByCityId(request);
        System.out.println(JSON.toJSONString(cityProductByCityId));
*/

        CityProductSearchRequest searchRequest = new CityProductSearchRequest();
        searchRequest.setCityId(30);
        searchRequest.setBusinessEnum(ImportBusinessEnum.news);
        searchRequest.setProductNoList(Lists.newArrayList("11114","14110","14102"));
        PageHelper.startPage(1, 10);
        List<CityProduct> cityProductByCityId = cityProductDao.findCityProductByCityId(searchRequest);
        System.out.println(JSON.toJSONString(cityProductByCityId));

    }
}