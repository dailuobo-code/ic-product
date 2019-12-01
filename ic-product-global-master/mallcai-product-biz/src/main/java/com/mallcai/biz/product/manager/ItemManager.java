package com.mallcai.biz.product.manager;

import com.google.common.collect.Maps;
import com.mallcai.domain.product.dto.CityDTO;
import com.mallcai.domain.product.dto.CityItemInfoDTO;
import com.mallcai.domain.product.dto.ItemInfoDTO;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.category.api.facade.BackCategoryReadFacade;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemListRelationRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 新商品服务管理
 */
@Component
public class ItemManager {
    @Autowired
    private com.mallcai.itemcenter.item.api.facade.CityItemReadFacade cityItemReadFacade;
    /**
     * 商品类目读取服务
     */
    @Autowired
    private BackCategoryReadFacade backCategoryReadFacade;

    public void filledRelationCity(List<ItemInfoDTO> itemInfoDTOList) {

        //todo just test


        List<Long> itemIdList = itemInfoDTOList.stream().map(ItemInfo::getId).collect(Collectors.toList());
        CityItemListRelationRequest cityItemListRelationRequest = new CityItemListRelationRequest();
        cityItemListRelationRequest.setItemIds(itemIdList);
        ICResponse<Map<Long, List<CityItemInfo>>> itemRelationsCityMap = cityItemReadFacade.listItemCityRelations(cityItemListRelationRequest);



        if (itemRelationsCityMap != null && itemRelationsCityMap.isSuccess() && MapUtils.isNotEmpty(itemRelationsCityMap.getResult())) {
            itemInfoDTOList.forEach(item -> {
                List<CityItemInfo> cityItemInfos = itemRelationsCityMap.getResult().get(item.getId());
                if(CollectionUtils.isEmpty(cityItemInfos)){
                    return;
                }
                List<Long> cityIdList = cityItemInfos.stream().map(CityItemInfo::getCityId).collect(Collectors.toList());
                List<CityDTO> cityDTOList = cityIdList.stream().map(t -> {
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setCityId(t);
                    cityDTO.setItemId(item.getId());
                    return cityDTO;
                }).collect(Collectors.toList());
                item.setDistributeCityList(cityDTOList);
            });
        }
    }

    public void filledRelationCityForCityItem(List<CityItemInfoDTO> itemInfoDTOList) {

        //todo just test


        List<Long> itemIdList = itemInfoDTOList.stream().map(CityItemInfoDTO::getItemId).collect(Collectors.toList());
        CityItemListRelationRequest cityItemListRelationRequest = new CityItemListRelationRequest();
        cityItemListRelationRequest.setItemIds(itemIdList);
        ICResponse<Map<Long, List<CityItemInfo>>> itemRelationsCityMap = cityItemReadFacade.listItemCityRelations(cityItemListRelationRequest);



        if (itemRelationsCityMap != null && itemRelationsCityMap.isSuccess() && MapUtils.isNotEmpty(itemRelationsCityMap.getResult())) {
            itemInfoDTOList.forEach(item -> {
                List<CityItemInfo> cityItemInfos = itemRelationsCityMap.getResult().get(item.getId());
                if(CollectionUtils.isEmpty(cityItemInfos)){
                    return;
                }
                List<Long> cityIdList = cityItemInfos.stream().map(CityItemInfo::getCityId).collect(Collectors.toList());
                List<CityDTO> cityDTOList = cityIdList.stream().map(t -> {
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setCityId(t);
                    cityDTO.setItemId(item.getId());
                    return cityDTO;
                }).collect(Collectors.toList());
                item.setDistributeCityList(cityDTOList);
            });
        }
    }

    /**
     * @param itemInfoDTOList
     */
    public void filCategoryInfo(List<ItemInfoDTO> itemInfoDTOList) {
        if (CollectionUtils.isEmpty(itemInfoDTOList)) {
            return;
        }
        List<Long> categoryIdList = itemInfoDTOList.stream().map(ItemInfoDTO::getCategoryId).distinct().collect(Collectors.toList());
        //category 与查询到的categoryList映射
        Map<Long, List<BackCategoryInfo>> categoryMapDtoList = Maps.newHashMap();
        categoryIdList.forEach(categoryId -> {
            ICResponse<List<BackCategoryInfo>> ancestorsOf = backCategoryReadFacade.findAncestorsOf(BackCategoryQueryRequest.builder().id(categoryId).build());
            if (ancestorsOf != null && ancestorsOf.isSuccess() && CollectionUtils.isNotEmpty(ancestorsOf.getResult())) {
                categoryMapDtoList.put(categoryId, ancestorsOf.getResult());
            }
        });

        itemInfoDTOList.forEach(itemInfoDTO -> {
            List<BackCategoryInfo> result = categoryMapDtoList.get(itemInfoDTO.getCategoryId());
            if (result != null) {
                if (CollectionUtils.isNotEmpty(result)) {
                    Optional<BackCategoryInfo> categoryInfo = result.stream().filter(t -> Objects.equals(t.getId(), itemInfoDTO.getCategoryId())).findFirst();
                    if (categoryInfo.isPresent()) {
                        itemInfoDTO.setCategoryName(categoryInfo.get().getName());
                        Optional<BackCategoryInfo> pCategory = result.stream().filter(t -> Objects.equals(t.getId(), categoryInfo.get().getPid())).findFirst();
                        pCategory.ifPresent(t -> itemInfoDTO.setPCategoryName(t.getName()));
                    }

                }
            }
        });

    }

    /**
     * @param itemInfoDTOList
     */
    public void filCategoryInfoForCityItem(List<CityItemInfoDTO> itemInfoDTOList) {
        if (CollectionUtils.isEmpty(itemInfoDTOList)) {
            return;
        }
        List<Long> categoryIdList = itemInfoDTOList.stream().map(CityItemInfoDTO::getCategoryId).distinct().collect(Collectors.toList());
        //category 与查询到的categoryList映射
        Map<Long, List<BackCategoryInfo>> categoryMapDtoList = Maps.newHashMap();
        categoryIdList.forEach(categoryId -> {
            ICResponse<List<BackCategoryInfo>> ancestorsOf = backCategoryReadFacade.findAncestorsOf(BackCategoryQueryRequest.builder().id(categoryId).build());
            if (ancestorsOf != null && ancestorsOf.isSuccess() && CollectionUtils.isNotEmpty(ancestorsOf.getResult())) {
                categoryMapDtoList.put(categoryId, ancestorsOf.getResult());
            }
        });

        itemInfoDTOList.forEach(itemInfoDTO -> {
            List<BackCategoryInfo> result = categoryMapDtoList.get(itemInfoDTO.getCategoryId());
            if (result != null) {
                if (CollectionUtils.isNotEmpty(result)) {
                    Optional<BackCategoryInfo> categoryInfo = result.stream().filter(t -> Objects.equals(t.getId(), itemInfoDTO.getCategoryId())).findFirst();
                    if (categoryInfo.isPresent()) {
                        itemInfoDTO.setCategoryName(categoryInfo.get().getName());
                        Optional<BackCategoryInfo> pCategory = result.stream().filter(t -> Objects.equals(t.getId(), categoryInfo.get().getPid())).findFirst();
                        pCategory.ifPresent(t -> itemInfoDTO.setPCategoryName(t.getName()));
                    }

                }
            }
        });

    }


}
