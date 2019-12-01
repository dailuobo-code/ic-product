package com.mallcai.itemcenter.item.api.facade;

import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.CityBatchBindToItemRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateStatusRequest;

/**
 * CityItemWriteFacade
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:34<br/>
 */
public interface CityItemWriteFacade {
    ICResponse<Boolean> batchBindCity(CityBatchBindToItemRequest request);

    ICResponse<Boolean> update(CityItemUpdateRequest request);

    ICResponse<Boolean> sellerUpdateStatus(CityItemUpdateStatusRequest request);
}
