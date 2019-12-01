package com.mallcai.api.product.backend;


import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemCreateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemDeleteRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateStatusRequest;

public interface ItemWriteFacade {
    ICResponse<Long> create(ItemCreateRequest request);

    ICResponse<Boolean> update(ItemUpdateRequest request);

    ICResponse<Boolean> delete(ItemDeleteRequest request);

    ICResponse<Boolean> sellerUpdateStatus(ItemUpdateStatusRequest request);
}
