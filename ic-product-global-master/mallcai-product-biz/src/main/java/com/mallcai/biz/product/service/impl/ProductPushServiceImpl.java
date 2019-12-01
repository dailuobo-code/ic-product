package com.mallcai.biz.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.ic.api.request.product.AsyncProductShelveRequest;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.dailuobo.ic.api.spec.SpecUpdateAsyncQueryRequest;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mallcai.api.product.backend.ProductPushService;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.biz.product.converter.CityItemProductConvert;
import com.mallcai.biz.product.manager.AsyncProductManager;
import com.mallcai.biz.product.model.HqProductTax;
import com.mallcai.domain.enums.AsyStatusEnum;
import com.mallcai.domain.product.AsyncProductLogDTO;
import com.mallcai.domain.product.AsyncProductLogDetail;
import com.mallcai.domain.product.dto.ProductCreateAsyDto;
import com.mallcai.domain.product.dto.SkuAttributeDTO;
import com.mallcai.domain.product.request.AddProductRequest;
import com.mallcai.domain.product.request.AsyProductRequest;
import com.mallcai.domain.product.request.CityProductStatusChangeRequest;
import com.mallcai.domain.product.request.ProductSpecModifyAsyncQueryRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.CitySkuInfo;
import com.mallcai.itemcenter.item.api.facade.CityItemReadFacade;
import com.mallcai.service.request.product.AsyProductCreateGroupRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 *
 */
@Slf4j
@Service("productPushService")
public class ProductPushServiceImpl implements ProductPushService {

    @Autowired
    private AsyncProductManager asyncProductManager;


    @Autowired
    private CityItemReadFacade cityItemReadFacade;


    @Override
    public PlainResult<AsyncProductLogDTO> asyCityProduct(AsyProductRequest request) {
        Map<Integer, FullCityItemWithDetailInfo> citySkuMap = asyncProductManager.listFullCityItemWithDetailInfo(request);

        String requestId = UUID.randomUUID().toString();
        AsyncProductLogDTO asyncProductLogDTO = new AsyncProductLogDTO();
        asyncProductLogDTO.setRequestParamJson(toJSONString(request));
        asyncProductLogDTO.setItemId(request.getItemIds());
        asyncProductLogDTO.setRequestUUId(requestId);

        String LV1Lv2Names = "";
        Integer taxId = 0;
        List<String> afConvert = new ArrayList<>();
        List<AsyncProductLogDetail> logDetails = new ArrayList<>();
        //转换数据
        for (Map.Entry<Integer, FullCityItemWithDetailInfo> entry : citySkuMap.entrySet()) {
            Integer cityId = entry.getKey();
            FullCityItemWithDetailInfo fullCityItemInfo = entry.getValue();
            List<ProductCreateAsyDto> asyProductDTOList = CityItemProductConvert.convert2Request(fullCityItemInfo);
            afConvert.add(JSON.toJSONString(asyncProductLogDTO));
            for (ProductCreateAsyDto productCreateDto : asyProductDTOList) {
                if (StringUtils.isEmpty(LV1Lv2Names)) {
                    LV1Lv2Names = asyncProductManager.getLv2Lv2Names(productCreateDto.getAddProductRequest().getCategoryId().longValue());
                }
                if (taxId == 0) {
                    Integer taxId1 = getTaxId(fullCityItemInfo.getItemInfo().getTaxCode());
                    taxId = MoreObjects.firstNonNull(taxId1, 0);
                }
                AddProductRequest addProductRequest = productCreateDto.getAddProductRequest();
                addProductRequest.setProductTaxId(taxId);

                /*<!------记录同步日志开始------>*/
                AsyncProductLogDetail asyncProductLogDetail = asyncProductManager.createHqProduct(addProductRequest);
                logDetails.add(asyncProductLogDetail);
                if (Objects.equals(asyncProductLogDetail.getAsyStatusEnum(), AsyStatusEnum.SUCCESS)) {
                    CityProductCreateRequest cityProductCreateRequest = productCreateDto.getCityProductCreateRequest();
                    cityProductCreateRequest.setHqProductId(addProductRequest.getHqProductId());
                    cityProductCreateRequest.setCityId(cityId);
                    cityProductCreateRequest.getSpec().setCityId(cityId);
                    cityProductCreateRequest.setL1L2Names(LV1Lv2Names);
                    AsyncProductLogDetail cityProductLog = asyncProductManager.createCityProduct(cityProductCreateRequest);
                    logDetails.add(cityProductLog);
                }
            }

            //创建规格组
            AsyProductCreateGroupRequest asyProductCreateGroupRequest = new AsyProductCreateGroupRequest();
            asyProductCreateGroupRequest.setCityId(cityId);
            asyProductCreateGroupRequest.setItemId(entry.getValue().getItemInfo().getItemId());
            asyProductCreateGroupRequest.setOperator("SYNC");
            asyProductCreateGroupRequest.setOperatorId(0);
            List<Long> skuIdList = fullCityItemInfo.getSkuInfoList().stream()
                    .map(CitySkuInfo::getSkuId).collect(Collectors.toList());
            asyProductCreateGroupRequest.setSkuIdList(skuIdList);
            asyProductCreateGroupRequest.setItemName(fullCityItemInfo.getItemInfo().getName());

            AsyncProductLogDetail creatProductGroupLog = asyncProductManager.creatProductGroup(asyProductCreateGroupRequest);
            logDetails.add(creatProductGroupLog);
            asyncProductLogDTO.setAfterConvertRequestParamJson(JSON.toJSONString(afConvert));
            asyncProductLogDTO.setAsyncProductLogDetailList(logDetails);
            asyncProductManager.createLog(asyncProductLogDTO);
            PlainResult.getDefaultSuccess(null);
        }
        return PlainResult.getDefaultSuccess(asyncProductLogDTO);
    }

    @Override
    public PlainResult<Void> asyncProductSpec(ProductSpecModifyAsyncQueryRequest request) {
        if (request == null) {
            return PlainResult.getDefaultFailed(500, "请求参数不能为空");
        }

        List<ProductSpecModifyAsyncQueryRequest.SkuUpdateDTO> skuUpdateDTOList = request.getSkuUpdateDTOList();
        if (CollectionUtils.isEmpty(skuUpdateDTOList)) {
            return null;
        }
        String requestId = UUID.randomUUID().toString();

        //日志记录
        AsyncProductLogDTO asyncProductLogDTO = new AsyncProductLogDTO();
        asyncProductLogDTO.setRequestParamJson(toJSONString(request));
        asyncProductLogDTO.setItemId(request.getItemId());
        asyncProductLogDTO.setRequestUUId(requestId);
        asyncProductLogDTO.setAfterConvertRequestParamJson(JSON.toJSONString(skuUpdateDTOList));

        //参数转换
        skuUpdateDTOList.forEach(citySku -> {
            JSONObject extraInfo = JSONObject.parseObject(citySku.getExtraJson());
            JSONObject extraPrice = JSONObject.parseObject(citySku.getPriceJSON());
            SpecUpdateAsyncQueryRequest specUpdateAsyncQueryRequest = new SpecUpdateAsyncQueryRequest();
            specUpdateAsyncQueryRequest.setCityId(request.getCityId());
            specUpdateAsyncQueryRequest.setItemId(request.getItemId());
            if (Objects.equals(request.getItemStatus(), 1)) {
                if (Objects.equals(citySku.getUpdateStatus(), 1)) {
                    specUpdateAsyncQueryRequest.setCityStatus(1);
                } else {
                    specUpdateAsyncQueryRequest.setCityStatus(2);
                }
            } else {
                specUpdateAsyncQueryRequest.setCityStatus(2);
            }
            // TODO: 2019-11-04 组合规格信息
            specUpdateAsyncQueryRequest.setThreshold(citySku.getThreshold());
            specUpdateAsyncQueryRequest.setDisablePrice(extraPrice.getDouble("disablePrice") * 0.01);
            specUpdateAsyncQueryRequest.setSkuId(citySku.getSkuId());
            specUpdateAsyncQueryRequest.setItemId(request.getItemId());
            specUpdateAsyncQueryRequest.setBarCode(citySku.getBarCode());
            Spec spec = new Spec();
            spec.setRealPrice(citySku.getPrice() * 0.01D);
            spec.setUpdateUserId(request.getUpdateUserId());
            spec.setAvgPrice(extraPrice.getDouble("avgPrice") * 0.01D);
            spec.setPackageMaxWeight(MoreObjects.firstNonNull(extraInfo.getInteger("packageWeight"), 1));
            spec.setPackageQuantity(MoreObjects.firstNonNull(extraInfo.getInteger("packageQuantity"), 0));
            String saleType = extraInfo.getString("salesType");
            if (Objects.equals("WEIGHT", saleType)) {
                spec.setUnitType(new Byte("1"));
            } else {
                spec.setUnitType(new Byte("2"));
            }

            specUpdateAsyncQueryRequest.setSpec(spec);
            AsyncProductLogDetail asyncProductLogDetail = asyncProductManager.updateProductSpec(specUpdateAsyncQueryRequest);
            asyncProductLogDTO.setAsyncProductLogDetailList(Lists.newArrayList(asyncProductLogDetail));
            asyncProductManager.createLog(asyncProductLogDTO);
        });

        return PlainResult.getDefaultSuccess(null);
    }


    private String getAttributeValue(List<SkuAttributeDTO> skuAttributeDTOS, String key) {
        Optional<SkuAttributeDTO> first = skuAttributeDTOS.stream().filter(t -> Objects.equals(t.getAttrKey(), key)).findFirst();

        if (first.isPresent()) {
            return first.get().getAttrVal();
        }
        return "";
    }

    @Override
    public PlainResult<Void> updateCityProductStatus(CityProductStatusChangeRequest request) {
        log.info("##updateCityProductStatus{}", request);
        String requestId = UUID.randomUUID().toString();
        AsyncProductLogDTO asyncProductLogDTO = new AsyncProductLogDTO();
        asyncProductLogDTO.setRequestParamJson(toJSONString(request));
        asyncProductLogDTO.setItemId(request.getItemId());
        asyncProductLogDTO.setRequestUUId(requestId);
        asyncProductLogDTO.setAfterConvertRequestParamJson(JSON.toJSONString(request));
        AsyncProductShelveRequest productShelveRequest = new AsyncProductShelveRequest();
        productShelveRequest.setItemId(request.getItemId());
        productShelveRequest.setCityId(request.getCityId());
        productShelveRequest.setUpdateBy(request.getUserId());
        productShelveRequest.setStatus(request.getStatus().getValue());
        AsyncProductLogDetail asyncProductLogDetail = asyncProductManager.shelveProduct(productShelveRequest);
        asyncProductLogDTO.setAsyncProductLogDetailList(Lists.newArrayList(asyncProductLogDetail));
        asyncProductManager.createLog(asyncProductLogDTO);
        return PlainResult.ok();

    }

    private String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    private Integer getTaxId(String taxCode) {
        List<HqProductTax> taxList = asyncProductManager.findTaxList(Lists.newArrayList(taxCode));
        if (CollectionUtils.isNotEmpty(taxList)) {
            return taxList.get(0).getId();
        }
        return null;
    }
}
