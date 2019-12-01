package com.mallcai.biz.category.service.impl;

import com.mallcai.api.category.AttributeBaseReadFacade;
import com.mallcai.itemcenter.category.api.bean.request.AttributeQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.CategoryAttributeInfo;
import com.mallcai.itemcenter.dto.ICResponse;
import org.mapstruct.MapMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mgrAttributeBaseReadService")
public class AttributeBaseReadService implements AttributeBaseReadFacade {

    @Autowired
    private com.mallcai.itemcenter.category.api.facade.AttributeBaseReadFacade attributeBaseReadFacade;

    @Override
    public ICResponse<List<CategoryAttributeInfo>> findAllByCategoryId(AttributeQueryRequest request) {
        if(request==null){
            throw new IllegalArgumentException("find_all_by_category_id request is null");
        }
        request.checkParam();
        return attributeBaseReadFacade.findAllByCategoryId(request);
    }
}
