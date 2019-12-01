package com.mallcai.api.product.backend;



import com.mallcai.domain.product.dto.CityItemInfoDTO;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemListRelationRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullCityItemQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullCityItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;

import java.util.List;
import java.util.Map;

public interface CityItemReadFacade {
    ICResponse<Paging<CityItemInfoDTO>> paging(CityItemPagingRequest request);

    ICResponse<FullCityItemInfo> queryFullCityItem(FullCityItemQueryRequest request);

    ICResponse<FullCityItemWithDetailInfo> queryFullCityItemWithDetail(FullCityItemWithDetailQueryRequest request);

    ICResponse<Map<Long, List<CityItemInfo>>> listItemCityRelations(CityItemListRelationRequest request);
}
