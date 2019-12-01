package com.dailuobo.ic.api.product.service;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.dto.ProductTopicDTO;
import com.mallcai.backend.common.api.Paging;

/**
 * MgrProductTopicReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 10:15<br/>
 */
public interface MgrProductTopicReadService {
    ICResponse<Paging<ProductTopicDTO>> pagingProductTopic();
}
