package com.mallcai.biz.product.service;

import com.mallcai.api.product.backend.CityItemWriteFacade;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.CityBatchBindToItemRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mgrCityItemWriteService")
public class CityItemWriteService implements CityItemWriteFacade {

    @Autowired
    com.mallcai.itemcenter.item.api.facade.CityItemWriteFacade cityItemWriteFacade;

    @Override
    public ICResponse<Boolean> batchBindCity(CityBatchBindToItemRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("batch_bind_city request is null");
        }
        request.checkParam();
        ICResponse<Boolean> booleanICResponse = cityItemWriteFacade.batchBindCity(request);
        return booleanICResponse;
    }

    @Override
    public ICResponse<Boolean> update(CityItemUpdateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("city item update request is null");
        }
        request.checkParam();
        ICResponse<Boolean> update = cityItemWriteFacade.update(request);
        return update;
    }

    @Override
    public ICResponse<Boolean> sellerUpdateStatus(CityItemUpdateStatusRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("seller_update_status request is null");
        }
        request.checkParam();
        ICResponse<Boolean> booleanICResponse = cityItemWriteFacade.sellerUpdateStatus(request);

        return booleanICResponse;
    }
}
