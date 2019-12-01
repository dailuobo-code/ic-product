package com.dailuobo.ic.api.product.service;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.product.bean.request.CreateProductGroupRequest;
import com.dailuobo.ic.api.product.bean.request.DeleteOneProductGroupRequest;
import com.dailuobo.ic.api.product.bean.request.UpdateOneProductGroupRequest;

/**
 * MgrProductGroupReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 10:00<br/>
 */
public interface MgrProductGroupWriteService {

    /**
     * 创建一个多规格商品组
     * @param request 创建单个多规格商品组请求 {@link CreateProductGroupRequest}
     * @return 商品组的 ID
     */
    ICResponse<Integer> createProductGroup(CreateProductGroupRequest request);

    /**
     * 更新多规格商品组名称、关联商品、状态
     * @param request 更新单个多规格商品组名称、关联商品、状态的请求 {@link UpdateOneProductGroupRequest}
     * @return 更新结果
     */
    ICResponse<Boolean> updateOneProductGroup(UpdateOneProductGroupRequest request);

    /**
     * 删除一个多规格商品组
     * @param request 删除单个多规格商品组请求 {@link CreateProductGroupRequest}
     * @return 操作结果
     */
    ICResponse<Boolean> deleteOneProductGroup(DeleteOneProductGroupRequest request);
}
