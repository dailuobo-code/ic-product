package com.mallcai.api.category;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.category.request.CategoryEditRequest;
import com.mallcai.domain.category.request.CategoryQueryRequest;
import com.mallcai.domain.category.request.PageCategoryQeueryRequest;
import com.mallcai.domain.category.request.GetHierarchicalCategoryRequest;

import javax.swing.*;
import java.util.List;

/**
 *商品分类服务，专注与mgr服务拆分
 */
public interface CategoryService  {


    /**
     * @param category 分类编辑入参  OperationEnum必传，区分是编辑还是新增
     * @return 是否操作成功
     */
    ICResponse<Boolean> editCategory(CategoryEditRequest category);

    /**
     * @param request 分类列表查询请求
     * @return 分类列表
     */
    ICResponse<List<CategoryDTO>> listCategory(CategoryQueryRequest request);

    /**
     * @param request 分类列表查询请求
     * @return 包含分页的分类类列表查询
     * code：操作状态码，msg，返回的消息，data：返回的数据，page：分页相关信息
     */
    ICResponse<List<CategoryDTO>> categoryPageList(PageCategoryQeueryRequest request);

    /**
     * 批量删除分类
     * @param categoryIdList 分类Id列表，需要做必要的检查
     * @return code：操作状态码，msg，返回的消息，
     */
    ICResponse<Boolean> delCategoryByIdList(List<Integer> categoryIdList);

    /**
     * 获取层级类目列表,如所有的一级类目、限定父类目的二级类目
     *
     * @param getHierarchicalCategoryRequest
     */
    ICResponse<List<CategoryDTO>> getHierarchicalCategory(GetHierarchicalCategoryRequest getHierarchicalCategoryRequest);

    /**
     * 加载分类信息
     * @param categoryId 分类Id
     * @return
     */
    ICResponse<CategoryDTO> loadCategoryById(Integer categoryId);


    /**
     * 根据分类Id列表 批量加载分类信息
     * @param categoryIdList 分类Id
     * @return 分类信息列表
     */
    ICResponse<List<CategoryDTO>> findByCategoryIdList(List<Integer> categoryIdList);


    ICResponse<Boolean> refreshALlSecondCategory();
}
