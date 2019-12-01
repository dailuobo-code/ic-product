package com.mallcai.biz.category.service.impl;

import com.mallcai.BaseTransactionalTest;
import com.mallcai.itemcenter.category.api.bean.request.AttributeQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.CategoryAttributeInfo;
import com.mallcai.itemcenter.category.api.facade.AttributeBaseReadFacade;
import com.mallcai.itemcenter.dto.ICResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class AttributeBaseReadServiceTest extends BaseTransactionalTest {
    @Autowired
    private AttributeBaseReadFacade attributeBaseReadFacade;

    @Test
    public void findAllByCategoryId() {
        AttributeQueryRequest request = new AttributeQueryRequest();
        request.setCategoryId(3L);
        ICResponse<List<CategoryAttributeInfo>> allByCategoryId = attributeBaseReadFacade.findAllByCategoryId(request);
        printJson(allByCategoryId);
    }
}