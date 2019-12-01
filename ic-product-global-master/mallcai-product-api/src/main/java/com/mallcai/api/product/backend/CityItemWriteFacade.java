package com.mallcai.api.product.backend;


import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.CityBatchBindToItemRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateStatusRequest;

public interface CityItemWriteFacade {

    ICResponse<Boolean> batchBindCity(CityBatchBindToItemRequest request);

    ICResponse<Boolean> update(CityItemUpdateRequest request);

    ICResponse<Boolean> sellerUpdateStatus(CityItemUpdateStatusRequest request);
}
