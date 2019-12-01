package com.mallcai.api.product.backend;

import com.mallcai.backend.common.api.Paging;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductTopicDTO;
import com.mallcai.domain.product.request.PagingProductTopicRequest;
import com.mallcai.domain.product.request.QueryActiveProductTopicRequest;
import com.mallcai.domain.product.request.QueryOneProductTopicRequest;

import java.util.List;

/**
 * MgrProductTopicReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 10:15<br/>
 */
public interface MgrProductTopicReadService {
    /**
     * 分页查询多规格商品组
     * @param request 分页查询参数 {@link PagingProductTopicRequest}
     * @return 多规格商品 {@link ProductTopicDTO}  的分页消息
     */
    ICResponse<Paging<ProductTopicDTO>> pagingProductTopic(PagingProductTopicRequest request);

    /**
     * 展示所有生效推荐主题
     * @param request 查询方法可以通过 {@link QueryActiveProductTopicRequest#instance()} 初始化
     * @return 生效的 {@link ProductTopicDTO} 列表
     */
    ICResponse<List<ProductTopicDTO>> queryActiveProductTopic(QueryActiveProductTopicRequest request);

    /**
     * 查询一个生效推荐主题
     * @param request 查询条件 {@link QueryOneProductTopicRequest}
     * @return 一个 {@link ProductTopicDTO} 或者 null
     */
    ICResponse<ProductTopicDTO> queryOneProductTopic(QueryOneProductTopicRequest request);
}
