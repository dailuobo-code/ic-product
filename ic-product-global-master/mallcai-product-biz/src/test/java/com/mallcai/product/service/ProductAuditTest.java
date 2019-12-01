package com.mallcai.product.service;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.vo.CityProductVo;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductSearchRequest;
import com.dailuobo.itemcenter.api.service.product.ItemCenterRouterService;
import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.GlobalProductService;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.search.common.utils.JsonMapper;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.request.SubmitProductAuditRequest;
import com.mallcai.router.client.RouterReference;
import com.mallcai.service.vo.Product4MgrVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ProductAuditTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/30 15:03<br/>
 */
public class ProductAuditTest extends BaseTransactionalTest {
    @Autowired
    private GlobalProductService globalProductService;

    @RouterReference
    @Reference(registry = "global" , group = "itemCenter" , timeout=5000)
    ItemCenterRouterService itemCenterRouterService;

    @Test
    public void testSubmit() {
        SubmitProductAuditRequest submit = new SubmitProductAuditRequest();
        submit.setComment("a test comment");
        submit.setProductIds(Lists.newArrayList(3, 6));
        submit.setOperator("test-admin");
        submit.setOperatorId(1111);

        ICResponse<Long> submitR = globalProductService.submitProductAudit(submit);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(submitR));
        assertThat(submitR.isSuccess()).isTrue();
    }


    @Test
    public void testGateWay(){
        Map<Integer, CityProductSearchRequest> requestMap = new HashedMap();
        CityProductSearchRequest request = new CityProductSearchRequest();
        request.setStoreId(3);
        request.setCityId(30);
        request.setCityStatus(2);
        requestMap.put(30,request);
        PlainResult<List<CityProduct>> cityProductByCityId = itemCenterRouterService.findCityProductByCityId(requestMap);
        System.out.println(JSON.toJSONString(cityProductByCityId));
    }


    @Test
    public void test(){
        Map<Integer, List<Integer>> map = new HashedMap();
        map.put(30,Lists.newArrayList(5120,288527,288518,288519,288520));
        Response<List<Product4MgrVO>> response = itemCenterRouterService.listStoreProduct(map);
        System.out.println(response);
    }


    @Test
    public void testSingleCityProduct(){
        Map<Integer, SingleCityProductQueryRequest> requestMap =new HashedMap();
        SingleCityProductQueryRequest request = new SingleCityProductQueryRequest();
        request.setCityId(30);
        request.setCityProductId(3);
        requestMap.put(30,request);
        PlainResult<CityProductVo> singleCityProductList = itemCenterRouterService.getSingleCityProductList(requestMap);
        System.out.println(JSON.toJSONString(singleCityProductList));

    }


    @Test
    public void testCityProduct(){
        Map<Integer, CityProductQueryRequest> var1 = new HashedMap();
        CityProductQueryRequest request = new CityProductQueryRequest();
        //request.setCityProductIds(Lists.newArrayList(3,6,9));
        request.setCityId(30);
        request.setOffset(0);
        request.setLimit(5);
        var1.put(30,request);
        PlainResult<Paging<CityProduct>> pagingPlainResult = itemCenterRouterService.productListByCity(var1);
        System.out.println(JSON.toJSONString(pagingPlainResult));
    }



}
