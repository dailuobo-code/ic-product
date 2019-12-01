package com.mallcai.api.product.backend;


import com.mallcai.domain.product.dto.ItemInfoDTO;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;

public interface ItemReadFacade {
    ICResponse<Paging<ItemInfoDTO>> paging(ItemPagingRequest request);

    ICResponse<FullItemInfo> queryFullItem(FullItemQueryRequest request);

    ICResponse<FullItemWithDetailInfo> queryItemForEdit(FullItemQueryRequest request);

    ICResponse<FullItemWithDetailInfo> queryFullItemWithDetail(FullItemWithDetailQueryRequest request);
}
