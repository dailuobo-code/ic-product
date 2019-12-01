package com.mallcai.biz.category.service.impl;

import com.mallcai.BaseTransactionalTest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryChildrenRequest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.dto.ICResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BackCategoryReadServiceTest extends BaseTransactionalTest {

    @Autowired
    private BackCategoryReadService backCategoryReadService;

    @Test
    public void findChildrenByPid() {
        BackCategoryQueryChildrenRequest request = new BackCategoryQueryChildrenRequest();
        request.setPid(0L);
        ICResponse<List<BackCategoryInfo>> childrenByPid = backCategoryReadService.findChildrenByPid(request);
        printJson(childrenByPid);
    }

    @Test
    public void findById() {
        BackCategoryQueryRequest request = new BackCategoryQueryRequest();
        request.setId(2L);
        ICResponse<BackCategoryInfo> byId = backCategoryReadService.findById(request);
        System.out.println(byId);
    }

    @Test
    public void findAncestorsOf() {
        BackCategoryQueryRequest request =new BackCategoryQueryRequest();
        request.setId(1L);
        ICResponse<List<BackCategoryInfo>> ancestorsOf = backCategoryReadService.findAncestorsOf(request);
        printJson(ancestorsOf);
        BackCategoryQueryRequest request1 =new BackCategoryQueryRequest();
        request1.setId(2L);
        ICResponse<List<BackCategoryInfo>> ancestorsOf1 = backCategoryReadService.findAncestorsOf(request1);


        BackCategoryQueryRequest request2 =new BackCategoryQueryRequest();
        request1.setId(3L);
        ICResponse<List<BackCategoryInfo>> ancestorsOf2 = backCategoryReadService.findAncestorsOf(request2);
        printJson(ancestorsOf2);
    }
}