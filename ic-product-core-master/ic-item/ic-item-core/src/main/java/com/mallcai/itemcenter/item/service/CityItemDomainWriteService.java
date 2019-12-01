package com.mallcai.itemcenter.item.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.mallcai.api.product.backend.ProductPushService;
import com.mallcai.domain.enums.ShelveOperationEnum;
import com.mallcai.domain.product.request.AsyProductRequest;
import com.mallcai.domain.product.request.CityProductStatusChangeRequest;
import com.mallcai.domain.product.request.ProductSpecModifyAsyncQueryRequest;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.converter.input.CityItemParamConverter;
import com.mallcai.itemcenter.item.api.converter.input.CitySkuParamConverter;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.item.manager.CityItemManager;
import com.mallcai.itemcenter.item.model.CityItem;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.item.repository.CityItemDAO;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.sku.repository.CitySkuDAO;
import com.mallcai.itemcenter.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CityItemDomainWriteService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:13<br/>
 */
@Slf4j
@Service
public class CityItemDomainWriteService {
    private final CitySkuParamConverter citySkuParamConverter;
    private final CityItemParamConverter cityItemParamConverter;

    private final CitySkuDAO citySkuDAO;
    private final CityItemDAO cityItemDAO;
    private final CityItemManager cityItemManager;

    @Reference(registry = "city", timeout = 5000, retries = 0, check = false)
    private ProductPushService productPushService;

    public CityItemDomainWriteService(CitySkuParamConverter citySkuParamConverter,
                                      CityItemParamConverter cityItemParamConverter,
                                      CitySkuDAO citySkuDAO,
                                      CityItemDAO cityItemDAO,
                                      CityItemManager cityItemManager) {
        this.citySkuParamConverter = citySkuParamConverter;
        this.cityItemParamConverter = cityItemParamConverter;
        this.citySkuDAO = citySkuDAO;
        this.cityItemDAO = cityItemDAO;
        this.cityItemManager = cityItemManager;
    }

    public Boolean batchBindCityToItem(Item item, List<Sku> skus, Set<Long> cityIds, Long sellerId) {
        // 去重
        List<CityItem> exists = cityItemDAO.findByItemId(item.getId());
        cityIds.removeAll(exists.stream().map(CityItem::getCityId).collect(Collectors.toSet()));
        if (cityIds.isEmpty()) {
            return Boolean.TRUE;
        }

        // 批量创建
        List<CityItem> cityItems = Lists.newArrayList();
        List<CitySku> citySkus = Lists.newArrayList();

        for (Long cityId : cityIds) {
            CityItem cityItem = cityItemParamConverter.item2city(item);
            cityItem.setStatus(ItemStatus.OFF_SHELF.getValue());
            cityItem.setUpdatedBy(sellerId);
            cityItem.setCityId(cityId);
            cityItems.add(cityItem);

            for (Sku sku : skus) {
                CitySku citySku = citySkuParamConverter.sku2Bind(sku);
                citySku.setCityId(cityId);
                citySku.setStatus(sku.getStatus());
                citySku.setUpdatedBy(sellerId);
                citySku.setThreshold(0);
                citySkus.add(citySku);
            }
        }

        // todo: 发送业务消息
        // todo: 批量绑定大量城市、有超时风险
        // sendMessage(new CityItemCreateEvent(new LinkedList<>(fullCityItemWithDetail)));
        Boolean creates = cityItemManager.creates(cityItems, citySkus);
        if (creates) {
            AsyProductRequest asyProductRequest = new AsyProductRequest();
            asyProductRequest.setCityIdList(Lists.newArrayList(cityIds));
            asyProductRequest.setItemIds(item.getId());
            asyProductRequest.setSellerId(sellerId);
            List<Long> skuIdList = skus.stream().map(Sku::getId).collect(Collectors.toList());
            asyProductRequest.setSkuId(skuIdList);
            asyCityProducts(asyProductRequest);
        }
        return creates;
    }


    @Async
    public void asyCityProducts(AsyProductRequest request) {
        productPushService.asyCityProduct(request);
    }

    public Boolean sellerUpdateStatus(List<IdVersionPair> targetList, Long cityId, Long sellerId, ItemStatus status, Long updatedBy) {
        try {
            for (IdVersionPair pair : targetList) {
                assertItemVersion(pair.getId(), sellerId, pair.getVersion());
            }

            cityItemManager.sellerUpdateStatus(targetList, cityId, sellerId, status.getValue(), updatedBy);
            asyUpdateStatus(cityId, targetList.get(0).getId(), sellerId, status, updatedBy);
            // todo: 发送业务消息
            // sendMessage(new CityItemUpdateEvent(new LinkedList<>(cityItemIdSet)));
            // sendMessage(new CitySkuUpdateEvent(new LinkedList<>(citySkuIdSet)));
            return Boolean.TRUE;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to update item status by targetList: {}, shopId: {}, status: {} updatedBy: {}, cause: {}",
                    targetList, sellerId, status, updatedBy, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.status.update.fail");
        }
    }

    private void assertItemVersion(Long id, Long sellerId, Integer version) {
        // todo: finish me，先不检查版本
    }

    public Boolean update(CityItem cityItem, List<CitySku> citySkus, Long sellerId, Long updatedBy) {
        // todo: 目前先更新价格
        Map<Long, CitySku> idToCitySku = citySkus.stream()
                .collect(Collectors.toMap(CitySku::getId, Function.identity()));

        List<CitySku> inDBSkus = citySkuDAO.findByIds(new ArrayList<>(idToCitySku.keySet()));
        if (CollectionUtils.isEmpty(inDBSkus)) {
            throw new ServiceException("data.error.for.missing.city.sku");
        }

        List<CitySku> toUpdate = Lists.newArrayList();
        for (CitySku sku : inDBSkus) {
            CitySku criteria = idToCitySku.get(sku.getId());
            CitySku update = new CitySku();
            update.setId(sku.getId());
            if (MapUtils.isNotEmpty(criteria.getExtra())) {
                update.setExtraJson(criteria.getExtraJson());
            }
            update.setPriceJson(JsonMapper.nonEmptyMapper().toJson(criteria.getExtraPrice()));
            update.setThreshold(criteria.getThreshold());
            update.setPrice(criteria.getPrice());
            update.setBarcode(criteria.getBarcode());
            update.setUpdatedBy(updatedBy);
            update.setStatus(criteria.getStatus());
            update.setSkuId(sku.getSkuId());

            toUpdate.add(update);
        }
        Boolean isOk = cityItemManager.update(cityItem, toUpdate, sellerId);
        if (isOk) {
            CityItem cityItemDBData = cityItemDAO.findById(cityItem.getId());
            ProductSpecModifyAsyncQueryRequest productSpecModifyAsyncQueryRequest = new ProductSpecModifyAsyncQueryRequest();
            productSpecModifyAsyncQueryRequest.setItemId(cityItemDBData.getItemId());
            productSpecModifyAsyncQueryRequest.setCityId(cityItemDBData.getCityId().intValue());
            productSpecModifyAsyncQueryRequest.setUpdateUserId(updatedBy.intValue());
            productSpecModifyAsyncQueryRequest.setItemStatus(cityItemDBData.getStatus());
            List<ProductSpecModifyAsyncQueryRequest.SkuUpdateDTO> skuUpdateDTOList = toUpdate.stream().map(citySku -> {
                ProductSpecModifyAsyncQueryRequest.SkuUpdateDTO skuUpdateDTO = new ProductSpecModifyAsyncQueryRequest.SkuUpdateDTO();
                skuUpdateDTO.setBarCode(citySku.getBarcode());
                skuUpdateDTO.setPrice(citySku.getPrice());
                skuUpdateDTO.setPriceJSON(citySku.getPriceJson());
                skuUpdateDTO.setThreshold(citySku.getThreshold());
                skuUpdateDTO.setSkuId(citySku.getSkuId());
                skuUpdateDTO.setUpdateBy(updatedBy);
                skuUpdateDTO.setUpdateStatus(citySku.getStatus());
                skuUpdateDTO.setExtraJson(citySku.getExtraJson());
                return skuUpdateDTO;
            }).collect(Collectors.toList());

            productSpecModifyAsyncQueryRequest.setSkuUpdateDTOList(skuUpdateDTOList);
            //异步同步老商品规格状态
            asyncSkuSpec(productSpecModifyAsyncQueryRequest);

        }

        // todo: 发送消息
        // sendMessage(new CitySkuUpdateEvent(new LinkedList<>(citySkuIdSet)));
        return isOk;
    }

    @Async
    void asyncSkuSpec(ProductSpecModifyAsyncQueryRequest productSpecModifyAsyncQueryRequest) {

        try {
            log.info("##asyncSkuSpec,requestParams:{}", JSON.toJSONString(productSpecModifyAsyncQueryRequest));
            productPushService.asyncProductSpec(productSpecModifyAsyncQueryRequest);
        } catch (Exception ex) {
            log.error(String.format("##asyncSkuSpec exception,request:%s", JSON.toJSONString(productSpecModifyAsyncQueryRequest)), ex);
        }
    }

    @Async
    public void asyUpdateStatus(Long cityId, Long id, Long sellerId, ItemStatus status, Long updateBy) {
        try {
            log.info("##AsyUpdateStatus,cityId:{},id:{},sellerId:{},status:{},updateBy:{}", cityId, id, sellerId, status, updateBy);
            CityItem cityItem = cityItemDAO.findById(id);
            CityProductStatusChangeRequest productStatusChangeRequest = new CityProductStatusChangeRequest();
            productStatusChangeRequest.setCityId(cityId.intValue());
            productStatusChangeRequest.setItemId(cityItem.getItemId());
            productStatusChangeRequest.setUserId(updateBy == null ? 0 : updateBy.intValue());
            productStatusChangeRequest.setSellerId(sellerId);
            productStatusChangeRequest.setStatus(Objects.equals(ItemStatus.ON_SHELF, status) ? ShelveOperationEnum.ON : ShelveOperationEnum.OFF);
            productPushService.updateCityProductStatus(productStatusChangeRequest);
        } catch (Exception ex) {
            log.error("AsyUpdateStatus error", ex);
        }
    }
}
