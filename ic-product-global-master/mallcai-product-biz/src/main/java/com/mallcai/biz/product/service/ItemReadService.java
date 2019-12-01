package com.mallcai.biz.product.service;

import com.mallcai.api.product.backend.ItemReadFacade;
import com.mallcai.biz.product.manager.ItemManager;
import com.mallcai.domain.product.dto.ItemInfoDTO;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("mgrItemReadFacade")
public class ItemReadService implements ItemReadFacade {

    @Autowired
    private com.mallcai.itemcenter.item.api.facade.ItemReadFacade itemReadFacade;
    @Autowired
    private com.mallcai.itemcenter.item.api.facade.CityItemReadFacade cityItemReadFacade;
    @Autowired
    ItemManager itemManager;

    @Override
    public ICResponse<Paging<ItemInfoDTO>> paging(ItemPagingRequest request) {
        request.checkParam();
        ICResponse<Paging<ItemInfo>> paging = itemReadFacade.paging(request);
        if (paging.isSuccess() && CollectionUtils.isNotEmpty(paging.getResult().getData())) {
            List<ItemInfo> data = paging.getResult().getData();
            //装配城市信息
            List<ItemInfoDTO> itemInfoDTOList = data.stream().map(t -> {
                ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
                BeanUtils.copyProperties(t, itemInfoDTO);
                return itemInfoDTO;
            }).collect(Collectors.toList());

            itemManager.filledRelationCity(itemInfoDTOList);
            itemManager.filCategoryInfo(itemInfoDTOList);
            Paging<ItemInfoDTO> pageResult = new Paging<>();
            pageResult.setTotal(paging.getResult().getTotal());

            pageResult.setData(itemInfoDTOList);
            return ICResponse.ok(pageResult);
        }
        return ICResponse.ok(Paging.empty());


    }

    @Override
    public ICResponse<FullItemInfo> queryFullItem(FullItemQueryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request is null");
        }
        request.checkParam();
        ICResponse<FullItemInfo> fullItemInfoICResponse = itemReadFacade.queryFullItem(request);
        return fullItemInfoICResponse;
    }

    @Override
    public ICResponse<FullItemWithDetailInfo> queryItemForEdit(FullItemQueryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request is null");
        }
        request.checkParam();

        ICResponse<FullItemWithDetailInfo> fullItemWithDetailInfoICResponse = itemReadFacade.queryItemForEdit(request);

        return fullItemWithDetailInfoICResponse;
    }

    @Override
    public ICResponse<FullItemWithDetailInfo> queryFullItemWithDetail(FullItemWithDetailQueryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request is null");
        }
        request.checkParam();
        try {
            ICResponse<FullItemWithDetailInfo> fullItemWithDetailInfoICResponse = itemReadFacade.queryFullItemWithDetail(request);
            return fullItemWithDetailInfoICResponse;
        } catch (Exception e) {
            return ICResponse.fail(e.getMessage());
        }
    }
}
