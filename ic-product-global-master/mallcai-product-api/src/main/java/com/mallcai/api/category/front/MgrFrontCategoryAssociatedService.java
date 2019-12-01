package com.mallcai.api.category.front;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.front.dto.FrontCategoryAssociatedConfigurationDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryAssociatedDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryAssociatedItemDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryDTO;
import com.mallcai.domain.category.front.request.*;

import java.util.List;

public interface MgrFrontCategoryAssociatedService {
    /**
     * 查询关联配置信息
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse<List<FrontCategoryAssociatedConfigurationDTO>> getAssociateConfigurationList(FrontCategoryAssociatedConfigurationQueryRequest request);

    /**
     * 查询关联的商品或后台类目
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse<List<FrontCategoryAssociatedItemDTO>> getAssociateItemList(FrontCategoryAssociatedItemQueryRequest request);

    /**
     * 保存关联数据
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse saveAssociated(FrontCategoryAssociatedSaveRequest request);

    /**
     * 删除关联关系
     * @param request 请求参数
     * @return 结果集
     */
    ICResponse deleteRelation(FrontCategoryAssociatedDeleteRequest request);
}
