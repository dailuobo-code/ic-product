package com.mallcai.api.category.front;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.front.dto.FrontCategoryDTO;
import com.mallcai.domain.category.front.request.*;

import java.util.List;

/**
 * 总部前台类目管理服务
 */
public interface MgrFrontCategoryService {
    /**
     * 获取前台类目列表,含二级类目
     *
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse<List<FrontCategoryDTO>> getCategoryList(FrontCategoryQueryRequest request);

    /**
     * 添加前台类目,新建类目和添加子类目
     *
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse<Long> addCategory(FrontCategoryAddRequest request);

    /**
     * 编辑类目
     *
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse editCategory(FrontCategoryEditRequest request);

    /**
     * 变更生效状态
     *
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse editCategoryEffectiveStatus(FrontCategoryEditEffectiveStatusRequest request);

    /**
     * 删除类目
     *
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse deleteCategory(FrontCategoryDeleteRequest request);

    ICResponse manuallyRefresh();

    ICResponse initCategory();
}
