package com.mallcai.api.product.backend;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.request.CreateProductTopicRequest;
import com.mallcai.domain.product.request.DeleteOneProductTopicRequest;
import com.mallcai.domain.product.request.UpdateOneProductTopicRequest;

/**
 * MgrProductTopicReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 10:15<br/>
 */
public interface MgrProductTopicWriteService {
    /**
     * 创建一个首页推荐分类
     * @param request 创建单个首页推荐分类请求 {@link CreateProductTopicRequest}
     * @return 商品组的 ID
     */
    ICResponse<Integer> createProductTopic(CreateProductTopicRequest request);

    /**
     * 更新首页推荐分类名称、关联商品、状态
     * @param request 更新单个首页推荐分类名称、关联类目、状态的请求 {@link UpdateOneProductTopicRequest}
     * @return 更新结果
     */
    ICResponse<Boolean> updateOneProductTopic(UpdateOneProductTopicRequest request);

    /**
     * 删除一个首页推荐分类
     * @param request 删除单个首页推荐分类请求 {@link DeleteOneProductTopicRequest}
     * @return 操作结果
     */
    ICResponse<Boolean> deleteOneProductTopic(DeleteOneProductTopicRequest request);
}
