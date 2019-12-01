package com.mallcai.itemcenter.item.api.facade;

import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemCreateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemDeleteRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateStatusRequest;

/**
 * ItemWriteFacade
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:19<br/>
 */
public interface ItemWriteFacade {
    ICResponse<Long> create(ItemCreateRequest request);

    ICResponse<Boolean> update(ItemUpdateRequest request);

    ICResponse<Boolean> delete(ItemDeleteRequest request);

    ICResponse<Boolean> sellerUpdateStatus(ItemUpdateStatusRequest request);
}
