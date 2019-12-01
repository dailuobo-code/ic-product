package com.dailuobo.ic.api.category.front;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.request.category.front.FCategoryRelatedContentQueryRequest;
import com.dailuobo.ic.api.request.category.front.FCategoryRelationQueryRequest;
import com.dailuobo.ic.dto.category.front.*;

import java.util.List;

/**
 * 城市的前台类目读服务
 */
public interface ICityFrontCategoryReadService {

    /**
     * <p>
     * 内部未实现，不要调用
     * 内部未实现，不要调用
     * 内部未实现，不要调用
     * </p>
     * 前台一级二级类目列表
     *
     * @return 全部的一级二级前台类目（非禁用状态的）
     */
    @Deprecated
    ICResponse<List<FrontCategoryDTO>> listFrontCategory();


    /**
     * 入参frontCategoryId为前台一级类目
     *
     * @param relationQueryRequest 城市前台二级类目关联关系查询
     * @return
     */
    ICResponse<List<FCategoryRelationDTO>> listCityFrontCategoryRelations(FCategoryRelationQueryRequest relationQueryRequest);


    /**
     * @param relatedBCategoryQueryRequest 前台类目关联的后台类目列表查询
     * @return
     */
    ICResponse<List<FCategoryRelatedBCategoryDTO>> listCityFrontCategoryRelatedBackendCategory(FCategoryRelatedContentQueryRequest relatedBCategoryQueryRequest);


    /**
     * 查询前台类目关联的商品列表
     *
     * @param relatedCityProductQueryRequest
     * @return
     */
    ICResponse<List<FrontCategoryRelatedCityProductDTO>> listCityFrontCategoryRelatedCityProduct(FCategoryRelatedContentQueryRequest relatedCityProductQueryRequest);


    /**
     * 查询前台类目关联的商品列表/后台类目列表
     *
     * @param relatedCityProductQueryRequest
     * @return
     */
    ICResponse<FrontCategoryRelatedItemDTO> listCityFrontCategoryRelatedItems(FCategoryRelatedContentQueryRequest relatedCityProductQueryRequest);


    /**
     * 查询前台类目关联的商品列表/后台类目列表
     *
     * @param cityId 城市id
     * @return
     */
    ICResponse<List<FrontCategoryRelatedItemDTO>> listAllCityFrontCategoryRelatedItems(Integer cityId);


}
