package com.mallcai.product.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.biz.product.manager.MgrProductServiceHelper;
import com.mallcai.biz.product.model.HqProductTax;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.request.*;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MgrProductServiceTest extends BaseTransactionalTest {

    @Resource
    private MgrProductService mgrProductService;

    @Resource
    private MgrProductServiceHelper mgrProductServiceHelper;

    @Test
    public void testAddProduct() {
        String productName = "乐凯军测试商品44433221";
        AddProductRequest add = AddProductRequest.builder().productName(productName).productNo("333229").categoryId(15).remark(productName+"之商品描述")
                .productIcon("a.png").productPics(Lists.newArrayList("b.png,c.png")).deliveryMode(2)
                .keepType(2).qualityTime(1).isStandard(2).pickClassify(1).productRate(BigDecimal.valueOf(10)).length("1").width("1").high("1")
                .productType(1).userId(1456).arrivalDay(7).isForecast(1).build();

        System.out.println("testAddProduct>>"+JSON.toJSONString(add));
        ICResponse<Boolean> booleanICResponse = mgrProductService.addProduct(add);
        System.out.println("testAddProduct>>resp>>"+JSON.toJSONString(booleanICResponse));
    }

    @Test
    public void testGetProductList() {
        GetProductListRequest get = new GetProductListRequest();
        get.setPageSize(10);
        get.setCurrentPage(1);
        get.setProductNo("17922");
        System.out.println(JSON.toJSONString(mgrProductService.getProductList(get)));
    }

    @Test
    public void testExistedProduct() {
        ExistedProductRequest get = new ExistedProductRequest();
        get.setProductNo("0120");
        //get.setProductName("abc");
        get.setCategoryId(15);
        System.out.println(JSON.toJSONString(mgrProductService.existedProduct(get)));
    }

    @Test
    public void testModifyProductName() {
        ModifyProductNameRequest get = new ModifyProductNameRequest();
        get.setProductId(8528);
        get.setProductName("王培坤测试商品0708心累");
        System.out.println(JSON.toJSONString(mgrProductService.modifyProductName(get)));
    }

    @Test
    public void testModifyProduct() {
        ModifyProductRequest get = new ModifyProductRequest();
        get.setProductId(65530);
        get.setRemark("乐凯军测试商品0708之商品描述心累");
        get.setDeliveryMode(2);
        get.setArrivalDay(2);
        get.setIsForecast(1);
        get.setUserId(22222);
        System.out.println(JSON.toJSONString(mgrProductService.modifyProduct(get)));
    }

    @Test
    public void testModifyProductStatus() {
        ModifyProductStatusRequest get = new ModifyProductStatusRequest();
        get.setUserId(1);
        get.setProductIds(Lists.newArrayList(8528));
        get.setStatus(0);
        System.out.println(JSON.toJSONString(mgrProductService.modifyProductStatus(get)));
    }

    @Test
    public void testGetProduct() {
        System.out.println(JSON.toJSONString(mgrProductService.getProduct(65530)));
    }

    @Test
    public void testGetHqProductDeliverExtraAttrs() {
        System.out.println(JSON.toJSONString(mgrProductService.getHqProductDeliverExtraAttrs(Lists.newArrayList(65530,65518))));
    }

    @Test
    public void testDelete() {
        System.out.println(JSON.toJSONString(mgrProductService.delete(Lists.newArrayList(8528))));
    }


    @Test
    public void testProductTax() {
        List<Integer> ids = Arrays.asList(1, 2, 3, 4);
        Map<Integer, HqProductTax> map = mgrProductServiceHelper.getProductTaxesMapByCodeList(ids);
        for (Map.Entry e : map.entrySet()) {
            System.out.println("============");
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }
    }

    @Test
    public void testAddPddProductAttr() {
        PddProductAttr attr = new PddProductAttr();
        attr.setProductNo("1111");
        attr.setPddGoodId("pdd1111");
        attr.setPddUrl("www.baidu.com");
        attr.setStatus(1);

        mgrProductServiceHelper.addPddProductAttr(attr);
        System.out.println("============");
        System.out.println(attr.getId() + "======" + attr.getUpdateTime());

    }

    @Test
    public void testGetPddProductAttrByProductNOs() {
        // dev环境
        //List<PddProductAttr> ret = mgrProductServiceHelper.getPddProductAttrByProductNOs(Arrays.asList("1111", "32"));
    }


}
