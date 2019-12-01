package com.mallcai.itemcenter.item.api.facade;

import com.google.common.base.Throwables;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.CityBatchBindToItemRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemUpdateStatusRequest;
import com.mallcai.itemcenter.item.api.converter.input.CityItemParamConverter;
import com.mallcai.itemcenter.item.api.converter.input.CitySkuParamConverter;
import com.mallcai.itemcenter.item.model.CityItem;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.item.service.CityItemDomainWriteService;
import com.mallcai.itemcenter.item.service.ItemDomainReadService;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.sku.service.SkuDomainReadService;
import com.mallcai.itemcenter.utils.GenericConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * CityItemWriteFacadeImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:23<br/>
 */
@Slf4j
@Component
public class CityItemWriteFacadeImpl implements CityItemWriteFacade {
    private final CitySkuParamConverter citySkuParamConverter;
    private final CityItemParamConverter cityItemParamConverter;

    private final SkuDomainReadService skuDomainReadService;
    private final ItemDomainReadService itemDomainReadService;

    private final CityItemDomainWriteService cityItemDomainWriteService;

    public CityItemWriteFacadeImpl(CitySkuParamConverter citySkuParamConverter,
                                   CityItemParamConverter cityItemParamConverter,
                                   SkuDomainReadService skuDomainReadService,
                                   ItemDomainReadService itemDomainReadService,
                                   CityItemDomainWriteService cityItemDomainWriteService) {
        this.citySkuParamConverter = citySkuParamConverter;
        this.cityItemParamConverter = cityItemParamConverter;
        this.skuDomainReadService = skuDomainReadService;
        this.itemDomainReadService = itemDomainReadService;
        this.cityItemDomainWriteService = cityItemDomainWriteService;
    }

    @Override
    public ICResponse<Boolean> batchBindCity(CityBatchBindToItemRequest request) {
        try {
            Item item = itemDomainReadService.findById(request.getItemId());
            if (item == null) {
                return ICResponse.fail("item.not.found");
            }
            if (!Objects.equals(item.getSellerId(), request.getSellerId())) {
                return ICResponse.fail("item.not.belong.to.seller");
            }

            List<Sku> skus = skuDomainReadService.findByItemId(item.getId(), true);
            if (CollectionUtils.isEmpty(skus)) {
                return ICResponse.fail("sku.not.found");
            }

            Boolean isOk = cityItemDomainWriteService.batchBindCityToItem(item, skus, new HashSet<>(request.getCityIds()), request.getSellerId());
            return ICResponse.ok(isOk);
        } catch (Exception e) {
            log.error("fail to batchBindCity by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> update(CityItemUpdateRequest request) {
        try {
            CityItem cityItem = cityItemParamConverter.dto2domain(request.getCityItemParam());
            List<CitySku> citySkus = GenericConverter.batchConvert(request.getCitySkuParamList(), citySkuParamConverter::dto2domain);
            citySkus.forEach(citySku -> {

            });
            Boolean isOk = cityItemDomainWriteService.update(cityItem, citySkus, request.getSellerId(), request.getUpdatedBy());
            return ICResponse.ok(isOk);
        } catch (Exception e) {
            log.error("fail to update by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> sellerUpdateStatus(CityItemUpdateStatusRequest request) {
        try {
            Boolean isOk = cityItemDomainWriteService.sellerUpdateStatus(request.getTargetList(), request.getCityId(), request.getSellerId(), request.getStatus(), request.getUpdatedBy());
            return ICResponse.ok(isOk);
        } catch (Exception e) {
            log.error("fail to sellerUpdateStatus by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }
}
