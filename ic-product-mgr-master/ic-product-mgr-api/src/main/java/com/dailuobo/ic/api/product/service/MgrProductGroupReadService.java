package com.dailuobo.ic.api.product.service;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.product.bean.request.PagingProductGroupRequest;
import com.dailuobo.ic.dto.ProductGroupDTO;
import com.mallcai.backend.common.api.Paging;

/**
 * MgrProductGroupReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 10:00<br/>
 */
public interface MgrProductGroupReadService {
    /**
     * 分页查询多规格商品组
     * @param request 分页查询参数 {@link PagingProductGroupRequest}
     * @return 多规格商品 {@link ProductGroupDTO}  的分页消息
     */
    ICResponse<Paging<ProductGroupDTO>> pagingProductGroup(PagingProductGroupRequest request);
}
