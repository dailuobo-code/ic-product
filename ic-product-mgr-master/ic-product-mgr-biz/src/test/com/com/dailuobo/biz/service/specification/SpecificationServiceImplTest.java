package com.dailuobo.biz.service.specification;

import com.dailuobo.BaseTest;
import com.dailuobo.api.specification.SpecificationService;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

public class SpecificationServiceImplTest extends BaseTest {


    @Autowired
    private SpecificationService specificationService;

    /**
     *
     */
    @Test
    public void getStoreSpecs() {
        Map<String, Object> param = new HashedMap();
        param.put("offset", 0);
        param.put("limit", 10);
        param.put("cityId", 30);
        param.put("storeId", 0);
        specificationService.getStoreSpecs(param);
    }
}