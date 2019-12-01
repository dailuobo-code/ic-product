package com.mallcai.biz.product.manager;

import com.mallcai.BaseTransactionalTest;
import com.mallcai.biz.product.model.HqProductCheckDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MgrProductServiceHelperTest extends BaseTransactionalTest {


    @Autowired
    private MgrProductServiceHelper mgrProductServiceHelper;
    @Test
    public void checkDumpHqProduct() {
        List<HqProductCheckDO> list = new ArrayList<>();
        HqProductCheckDO hqProductCheckDO = new HqProductCheckDO();
        hqProductCheckDO.setBarCode("8888");
        hqProductCheckDO.setSkuCode("11111");
        hqProductCheckDO.setSkuName("测试");
        list.add(hqProductCheckDO);


        HqProductCheckDO hqProductCheckDO1 = new HqProductCheckDO();
        hqProductCheckDO1.setBarCode("88889");
        hqProductCheckDO1.setSkuCode("111119");
        hqProductCheckDO1.setSkuName("测试1");
        list.add(hqProductCheckDO1);


        HqProductCheckDO hqProductCheckDO2 = new HqProductCheckDO();
        hqProductCheckDO2.setBarCode("88889");
        hqProductCheckDO2.setSkuCode("111119");
        hqProductCheckDO2.setSkuName("测试1");
        list.add(hqProductCheckDO2);
        mgrProductServiceHelper.checkDumpHqProduct(list);
    }
}