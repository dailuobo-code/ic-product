package com.mallcai.biz.category.service.impl;

import com.mallcai.api.category.BackCategoryReadFacade;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryChildrenRequest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.dto.ICResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mgrBackCategoryReadService")
public class BackCategoryReadService implements BackCategoryReadFacade {

    @Autowired
    private com.mallcai.itemcenter.category.api.facade.BackCategoryReadFacade  backCategoryReadFacade;

    @Override
    public ICResponse<List<BackCategoryInfo>> findChildrenByPid(BackCategoryQueryChildrenRequest request) {
        return backCategoryReadFacade.findChildrenByPid(request);
    }

    @Override
    public ICResponse<BackCategoryInfo> findById(BackCategoryQueryRequest request) {
        ICResponse<BackCategoryInfo> byId = backCategoryReadFacade.findById(request);
        return byId;
    }

    @Override
    public ICResponse<List<BackCategoryInfo>> findAncestorsOf(BackCategoryQueryRequest request) {
        ICResponse<List<BackCategoryInfo>> ancestorsOf = backCategoryReadFacade.findAncestorsOf(request);
        return ancestorsOf;
    }
}
