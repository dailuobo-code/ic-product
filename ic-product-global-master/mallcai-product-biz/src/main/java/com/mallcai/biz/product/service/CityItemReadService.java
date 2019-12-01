package com.mallcai.biz.product.service;

import com.mallcai.api.product.backend.CityItemReadFacade;
import com.mallcai.biz.product.manager.ItemManager;
import com.mallcai.domain.product.dto.CityItemInfoDTO;
import com.mallcai.domain.product.dto.ItemInfoDTO;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.*;
import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("mgrCityItemReadService")
public class CityItemReadService implements CityItemReadFacade {

    @Autowired
    private com.mallcai.itemcenter.item.api.facade.CityItemReadFacade cityItemReadFacade;
    @Autowired
    ItemManager itemManager;

    @Override
    public ICResponse<Paging<CityItemInfoDTO>> paging(CityItemPagingRequest request) {
        if(request==null){
            throw new IllegalArgumentException("request is null");
        }
        request.checkParam();
        ICResponse<Paging<CityItemInfo>> cityItemDate = cityItemReadFacade.paging(request);
        if (cityItemDate.isSuccess() && CollectionUtils.isNotEmpty(cityItemDate.getResult().getData())) {
            List<CityItemInfo> data = cityItemDate.getResult().getData();
            //装配城市信息
            List<CityItemInfoDTO> itemInfoDTOList = data.stream().map(t -> {
                CityItemInfoDTO itemInfoDTO = new CityItemInfoDTO();
                BeanUtils.copyProperties(t, itemInfoDTO);
                return itemInfoDTO;
            }).collect(Collectors.toList());

            itemManager.filledRelationCityForCityItem(itemInfoDTOList);
            itemManager.filCategoryInfoForCityItem(itemInfoDTOList);
            Paging<CityItemInfoDTO> pageResult = new Paging<>();
            pageResult.setTotal(cityItemDate.getResult().getTotal());

            pageResult.setData(itemInfoDTOList);
            return ICResponse.ok(pageResult);
        }
        return ICResponse.ok(Paging.empty());
    }

    @Override
    public ICResponse<FullCityItemInfo> queryFullCityItem(FullCityItemQueryRequest request) {
        if(request==null){
            throw new IllegalArgumentException("request is null");
        }
        request.checkParam();
        ICResponse<FullCityItemInfo> fullCityItemInfoICResponse = cityItemReadFacade.queryFullCityItem(request);
        return fullCityItemInfoICResponse;
    }

    @Override
    public ICResponse<FullCityItemWithDetailInfo> queryFullCityItemWithDetail(FullCityItemWithDetailQueryRequest request) {
        if(request==null){
            throw new IllegalArgumentException("request is null");
        }
        request.checkParam();
        ICResponse<FullCityItemWithDetailInfo> fullCityItemWithDetailInfoICResponse = cityItemReadFacade.queryFullCityItemWithDetail(request);


        return fullCityItemWithDetailInfoICResponse;
    }

    @Override
    public ICResponse<Map<Long, List<CityItemInfo>>> listItemCityRelations(CityItemListRelationRequest request) {
        if(request==null){
            throw new IllegalArgumentException("request is null");
        }
        request.checkParam();
        ICResponse<Map<Long, List<CityItemInfo>>> mapICResponse = cityItemReadFacade.listItemCityRelations(request);
        return mapICResponse;
    }
}
