package com.mallcai.api.category;



import com.mallcai.itemcenter.category.api.bean.request.AttributeQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.CategoryAttributeInfo;
import com.mallcai.itemcenter.dto.ICResponse;

import java.util.List;

public interface AttributeBaseReadFacade {
    /**
     * 根据后台类目id查询挂在该类目下的属性列表(包括从父类目继承的属性,去重复)
     *
     * @param request
     * @return
     */
    ICResponse<List<CategoryAttributeInfo>> findAllByCategoryId(AttributeQueryRequest request);
}
