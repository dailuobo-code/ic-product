package com.dailuobo.ic.api.category.front;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.request.category.front.FCategoryRelationDeleteRequest;
import com.dailuobo.ic.api.request.category.front.FrontCategoryRelationsCreateRequest;

/**
 * 城市前台类目写服务
 */
public interface ICityFrontCategoryWriteService {


    /**
     * @param request 创建前台类目跟后台类目/商品关联关系请求
     * @return 是否创建成功
     */
    ICResponse<Boolean> saveFCategoryRelation(FrontCategoryRelationsCreateRequest request);

    /**
     * @param request 删除前台类目关联的后台类目/商品的请求
     * @return 是否删除成功
     */
    ICResponse<Boolean> delFCategoryRelation(FCategoryRelationDeleteRequest request);
}
