package com.mallcai.itemcenter.item.api.facade;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemListRelationRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullCityItemQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullCityItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.converter.output.CityItemInfoConverter;
import com.mallcai.itemcenter.item.model.CityItem;
import com.mallcai.itemcenter.item.model.FullCityItemBO;
import com.mallcai.itemcenter.item.model.FullItemBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.service.CityItemDomainReadService;
import com.mallcai.itemcenter.item.service.ItemDomainReadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CityItemReadFacadeImple
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:22<br/>
 */
@Slf4j
@Component
public class CityItemReadFacadeImpl implements CityItemReadFacade {

    private final CityItemInfoConverter cityItemInfoConverter;

    private final ItemDomainReadService itemDomainReadService;
    private final CityItemDomainReadService cityItemDomainReadService;

    public CityItemReadFacadeImpl(CityItemInfoConverter cityItemInfoConverter,
                                  ItemDomainReadService itemDomainReadService,
                                  CityItemDomainReadService cityItemDomainReadService) {
        this.cityItemInfoConverter = cityItemInfoConverter;
        this.itemDomainReadService = itemDomainReadService;
        this.cityItemDomainReadService = cityItemDomainReadService;
    }

    @Override
    public ICResponse<Paging<CityItemInfo>> paging(CityItemPagingRequest request) {
        try {
            Paging<CityItem> cityItemPaging = cityItemDomainReadService.paging(request);
            if (cityItemPaging.isEmpty()) {
                return ICResponse.ok(Paging.empty());
            }

            Paging<CityItemInfo> result = new Paging<>();
            result.setTotal(cityItemPaging.getTotal());
            result.setData(convert(cityItemPaging.getData()));

            return ICResponse.ok(result);
        } catch (Exception e) {
            log.error("fail to paging by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<FullCityItemInfo> queryFullCityItem(FullCityItemQueryRequest request) {
        try {
            FullItemBO fullItemBO = itemDomainReadService.findFullItemById(request.getItemId());
            if (fullItemBO.getItem() == null || fullItemBO.getItemDetail() == null || CollectionUtils.isEmpty(fullItemBO.getSkuList())) {
                return ICResponse.fail("item.not.found");
            }
            FullCityItemBO fullCityItemBO = cityItemDomainReadService.findFullItemById(request.getItemId(), request.getCityId());

            // todo: 提供扩展入口

            return ICResponse.ok(cityItemInfoConverter.getWithImgs(fullCityItemBO, fullItemBO));
        } catch (Exception e) {
            log.error("fail to queryFullCityItem by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<FullCityItemWithDetailInfo> queryFullCityItemWithDetail(FullCityItemWithDetailQueryRequest request) {
        try {
            FullCityItemBO fullCityItemBO = cityItemDomainReadService.findFullItemById(request.getItemId(), request.getCityId());
            FullItemBO fullItemBO = itemDomainReadService.findFullItemById(request.getItemId());
            if (fullItemBO.getItem() == null || fullItemBO.getItemDetail() == null || CollectionUtils.isEmpty(fullItemBO.getSkuList())) {
                return ICResponse.fail("item.not.found");
            }

            // todo: 提供扩展入口

            return ICResponse.ok(cityItemInfoConverter.getWithDetail(fullCityItemBO, fullItemBO));
        } catch (Exception e) {
            log.error("fail to queryFullCityItemWithDetail by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<Map<Long, List<CityItemInfo>>> listItemCityRelations(CityItemListRelationRequest request) {
        try {
            List<CityItem> cityItems = cityItemDomainReadService.listBy(request);
            if (cityItems.isEmpty()) {
                log.error("city item not found by {}", request);
                return ICResponse.ok(Collections.emptyMap());
            }

            List<CityItemInfo> result = convert(cityItems);
            if (result.isEmpty()) {
                return ICResponse.ok(Collections.emptyMap());
            }

            Map<Long, List<CityItemInfo>> map = Maps.newHashMap();
            for (CityItemInfo info : result) {
                map.compute(info.getItemId(), (id, list) -> {
                    if (CollectionUtils.isEmpty(list)) {
                        return Lists.newArrayList(info);
                    } else {
                        list.add(info);
                        return list;
                    }
                });
            }
            return ICResponse.ok(map);
        } catch (Exception e) {
            log.error("fail to listItemCityRelations by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    private List<CityItemInfo> convert(List<CityItem> cityItems) {
        List<Item> items = itemDomainReadService.listBy(
                ImmutableMap.of("ids",
                        cityItems.stream().map(CityItem::getItemId).collect(Collectors.toList())));

        if (items.isEmpty()) {
            log.error("item not found by {}", cityItems);
            return Collections.emptyList();
        }

        Map<Long, Item> idToItem = items.stream().collect(Collectors.toMap(Item::getId, Function.identity()));
        List<CityItemInfo> result = Lists.newArrayList();
        for (CityItem cityItem : cityItems) {
            Item exist = idToItem.get(cityItem.getItemId());
            if (exist == null) {
                continue;
            }

            CityItemInfo info = cityItemInfoConverter.domain2dto(exist, cityItem);
            result.add(info);
        }
        return result;
    }
}
