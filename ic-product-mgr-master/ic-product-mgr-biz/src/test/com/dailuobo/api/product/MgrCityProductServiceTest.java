package com.dailuobo.api.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.CityProductSyncCity;
import com.dailuobo.api.enums.ForecastFlagEnum;
import com.dailuobo.ic.api.goods.IProductGoodsRelService;
import com.dailuobo.ic.api.request.GetCityProductListRequest;
import com.dailuobo.ic.api.request.ProductForecastFlagChangeRequest;
import com.google.common.collect.Lists;
import com.mallcai.bs.dao.CityProductDao;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MgrCityProductServiceTest extends BaseTest {

    @Autowired
    private MgrCityProductService mgrCityProductService;

    @Autowired
    private CityProductDao cityProductDao;

    @Autowired
    private MgrMerchantCityProductService mgrMerchantCityProductService;

    @Autowired
    private IProductGoodsRelService productGoodsRelService;

    /**
     *
     */
    @Test
    public void selectAll() {

        GetCityProductListRequest listRequest = new GetCityProductListRequest();
        listRequest.setOffset(0);
        listRequest.setLimit(10);
        listRequest.setCityId(5);
        //listRequest.setProductNo("10231");
        listRequest.setCityProductIds (Lists.newArrayList(292604,292046,291920,291919).toArray(new Integer[0]));

       // listRequest.setFlagEnum(ForecastFlagEnum.ENABLE);
        ICResponse<List<CityProduct>> listICResponse = mgrCityProductService.selectAll(listRequest);
        System.out.println(JSON.toJSONString(listICResponse));
        Assert.assertNotNull(listICResponse);
        Assert.assertNotNull(listICResponse.getData());
        for (CityProduct cityProduct : listICResponse.getData()){
            Assert.assertNotNull(cityProduct);
            Assert.assertNotNull(cityProduct.getIsForecast());
            Assert.assertEquals(cityProduct.getIsForecast(), Integer.valueOf(0));

        }

    }


    @Test
    public void testCityProductForecastFlagChange(){
        ProductForecastFlagChangeRequest request = new ProductForecastFlagChangeRequest();
        request.setCityId(30);
        request.setCityProductIdList(Lists.newArrayList(3,6,9));
        request.setForecastFlagEnum(ForecastFlagEnum.ENABLE);
        request.setOperatorId(1);
        ICResponse<Boolean> booleanICResponse = mgrCityProductService.changeForecastFlag(request);
        Assert.assertTrue(booleanICResponse.getData());
        //ICResponse<CityProductSyncCity> cityProductById = mgrCityProductService.getCityProductById(3);
        //11102

        GetCityProductListRequest listRequest = new GetCityProductListRequest();
        listRequest.setOffset(0);
        listRequest.setLimit(10);
        listRequest.setCityId(30);
        listRequest.setFlagEnum(ForecastFlagEnum.ENABLE);
        ICResponse<List<CityProduct>> listICResponse = mgrCityProductService.selectAll(listRequest);

        Assert.assertEquals(listICResponse.getData().size(),2);
        Assert.assertEquals(listICResponse.getData().get(0).getIsForecast(),Integer.valueOf(1));
    }

    @Test
    public void testIsTodayForcastProduct() {
        // 需要在redis里面设置sadd

        ICResponse<Map<Integer, Boolean>> ret = mgrCityProductService.isTodayForcastProduct(30, Arrays.asList(99));
        System.out.println("===========输出结果============");
        System.out.println(ret.getCode());
        for (Map.Entry e : ret.getData().entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }
    }

    @Test
    public void testBatchUpdateCityStatus() {
        ICResponse<Boolean> ret = mgrCityProductService.batchUpdateCityStatus(30, Arrays.asList(99), 2, 1111);
        System.out.println("===========输出结果============");
        System.out.println(ret.getCode());
        System.out.println(ret.getData());
    }

    @Test
    public void testMerchantProductRate() {
        CityProduct product = new CityProduct();
        product.setCityProductId(275528);
        product.setMerchantId(11222);
        product.setMerchantRate(new BigDecimal("0.2222"));
        cityProductDao.addOrUpdateMerchantProductRate(product);
    }

    @Test
    public void testMgrMerchantCityProductService() {
        Map<String, Object> param = new HashMap<>();
        param.put("cityId", 30);
        param.put("goodsType", 1);
        param.put("offset", 0);
        param.put("limit", 10);
       // param.put("productNo",36589);
        ICResponse<List<CityProduct>> ret = mgrMerchantCityProductService.selectAll(30, param, 0, 10);
        System.out.println(ret);
    }


    /**
     *
     */
    @Test
    public void testGoodsRel() {
        GetCityProductListRequest listRequest = new GetCityProductListRequest();
        listRequest.setOffset(0);
        listRequest.setLimit(10);
        listRequest.setCityId(30);
        listRequest.setIsGoodsRel(1);

        ICResponse<List<CityProduct>> listICResponse = mgrCityProductService.selectAll(listRequest);
        System.out.println(JSON.toJSONString(listICResponse));
        Assert.assertNotNull(listICResponse);
        Assert.assertNotNull(listICResponse.getData());
        for (CityProduct cityProduct : listICResponse.getData()){
            Assert.assertNotNull(cityProduct);
            Assert.assertNotNull(cityProduct.getIsGoodsRel());
            Assert.assertEquals(cityProduct.getIsGoodsRel(), Integer.valueOf(1));
        }

    }

    /**
     *
     */
    @Test
    public void testGoodsRelIsNull() {
        GetCityProductListRequest listRequest = new GetCityProductListRequest();
        listRequest.setOffset(0);
        listRequest.setLimit(10);
        listRequest.setCityId(30);
        listRequest.setIsGoodsRel(0);

        ICResponse<List<CityProduct>> listICResponse = mgrCityProductService.selectAll(listRequest);
        System.out.println(JSON.toJSONString(listICResponse));
        Assert.assertNotNull(listICResponse);
        Assert.assertNotNull(listICResponse.getData());
        for (CityProduct cityProduct : listICResponse.getData()){
            Assert.assertNotNull(cityProduct);
            Assert.assertNotNull(cityProduct.getIsGoodsRel());
            Assert.assertEquals(cityProduct.getIsGoodsRel(), Integer.valueOf(0));
        }

    }
    @Test
    public void testCityProductCopy() {
        ICResponse<CityProductSyncCity> cityProductById = mgrCityProductService.getCityProductById(291469);
        CityProductSyncCity cityProductSyncCity = cityProductById.getData();
        cityProductSyncCity.setCityId(27);
        cityProductSyncCity.setUserId(1000);
        ICResponse<Boolean> booleanICResponse = mgrCityProductService.cityProductCopyOtherCity(cityProductSyncCity);
        System.out.println(JSON.toJSONString(booleanICResponse));


    }

    @Test
    public void testIsStandard() {
        GetCityProductListRequest listRequest = new GetCityProductListRequest();
        listRequest.setOffset(0);
        listRequest.setLimit(10);
        listRequest.setCityId(30);
        listRequest.setProductNo("11123");

        ICResponse<List<CityProduct>> listICResponse = mgrCityProductService.selectAll(listRequest);
        System.out.println(JSON.toJSONString(listICResponse));
        Assert.assertNotNull(listICResponse);
        Assert.assertNotNull(listICResponse.getData());
        for (CityProduct cityProduct : listICResponse.getData()){
            Assert.assertNotNull(cityProduct);
        }

    }


}