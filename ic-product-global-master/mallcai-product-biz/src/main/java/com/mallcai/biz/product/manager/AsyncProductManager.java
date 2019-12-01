package com.mallcai.biz.product.manager;

import com.alibaba.fastjson.JSON;
import com.dailuobo.ic.api.request.product.AsyncProductShelveRequest;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.dailuobo.ic.api.spec.SpecUpdateAsyncQueryRequest;
import com.dailuobo.itemcenter.api.service.product.ItemCenterRouterService;
import com.google.common.collect.Lists;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.biz.product.dao.mapper.ProductAsyLogMapper;
import com.mallcai.biz.product.dao.mapper.TblProductTaxMapper;
import com.mallcai.biz.product.model.AsyProductLogDO;
import com.mallcai.biz.product.model.HqProductTax;
import com.mallcai.domain.enums.AsyStatusEnum;
import com.mallcai.domain.enums.AsyncLogTypeEnum;
import com.mallcai.domain.product.AsyncProductLogDTO;
import com.mallcai.domain.product.AsyncProductLogDetail;
import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.domain.product.request.AddProductRequest;
import com.mallcai.domain.product.request.AsyProductRequest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.category.api.facade.BackCategoryReadFacade;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.FullCityItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.CitySkuInfo;
import com.mallcai.itemcenter.item.api.facade.CityItemReadFacade;
import com.mallcai.router.client.RouterReference;
import com.mallcai.service.request.product.AsyProductCreateGroupRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AsyncProductManager {


    @Autowired
    private MgrProductService mgrProductService;

    @RouterReference
    ItemCenterRouterService itemCenterRouterService;

    @Autowired
    private ProductAsyLogMapper productAsyLogMapper;

    @Autowired
    private MgrProductServiceHelper mgrProductServiceHelper;


    @Autowired
    private CityItemReadFacade cityItemReadFacade;


    @Autowired
    private BackCategoryReadFacade backCategoryReadFacade;


    @Autowired
    private TblProductTaxMapper tblProductTaxMapper;

    /**
     * 异步创建日志
     *
     * @param asyncProductLogDTO
     */
    @Async
    public void createLog(AsyncProductLogDTO asyncProductLogDTO) {
        AsyProductLogDO asyncProductLogDO = new AsyProductLogDO();
        BeanUtils.copyProperties(asyncProductLogDTO, asyncProductLogDO);
        productAsyLogMapper.insert(asyncProductLogDO);
    }

    /**
     * 检查对应的城市总部是否已经创建
     *
     * @param addProductRequest
     * @return
     */
    public AsyncProductLogDetail createHqProduct(AddProductRequest addProductRequest) {
        AsyncProductLogDetail asyncProductLogDetail = new AsyncProductLogDetail();
        asyncProductLogDetail.setRequestString(JSON.toJSONString(addProductRequest));
        asyncProductLogDetail.setAsyncLogTypeEnum(AsyncLogTypeEnum.HQ_PRODUCT_ASYNC);
        Long itemId = addProductRequest.getItemId();
        Long skuId = addProductRequest.getSkuId();
        try {
            asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.SUCCESS);
            String productNo = addProductRequest.getProductNo();
            ProductDTO productDTO = mgrProductServiceHelper.loadProductsByItemId(itemId, productNo);
            if (productDTO != null) {
                addProductRequest.setHqProductId(productDTO.getProductId());
            } else {
                com.mallcai.common.ICResponse<Boolean> hqProductAsyResult = mgrProductService.addProduct(addProductRequest);
                if (hqProductAsyResult == null || !hqProductAsyResult.isSuccess()) {
                    asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.Fail);
                }
                asyncProductLogDetail.setResponseString(JSON.toJSONString(hqProductAsyResult));
            }
            return asyncProductLogDetail;
        } catch (Exception ex) {
            log.error("createHqProduct error", ex);
            asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.Fail);
            asyncProductLogDetail.setResponseString(ex.getMessage());
            return asyncProductLogDetail;
        }

    }

    public AsyncProductLogDetail createCityProduct(CityProductCreateRequest cityProductCreateRequest) {
        AsyncProductLogDetail asyncProductLogDetail = new AsyncProductLogDetail();
        asyncProductLogDetail.setRequestString(JSON.toJSONString(cityProductCreateRequest));
        asyncProductLogDetail.setAsyncLogTypeEnum(AsyncLogTypeEnum.CITY_PRODUCT_ASYNC);
        asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.SUCCESS);
        Map<Integer, CityProductCreateRequest> cityProductCreateRequestMap = new HashedMap();
        Integer cityId = cityProductCreateRequest.getCityId();
        cityProductCreateRequestMap.put(cityId, cityProductCreateRequest);
        try {
            Map<Integer, PlainResult<Boolean>> integerResponseMap = itemCenterRouterService.multiCityCreateProduct(cityProductCreateRequestMap);
            asyncProductLogDetail.setResponseString(JSON.toJSONString(integerResponseMap));
            return asyncProductLogDetail;

        } catch (Exception ex) {
            log.error("createCityProduct error", ex);
            asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.Fail);
            asyncProductLogDetail.setResponseString(JSON.toJSONString(ex.getMessage()));
            return asyncProductLogDetail;
        }
    }


    public AsyncProductLogDetail creatProductGroup(AsyProductCreateGroupRequest asyProductCreateGroupRequest) {
        AsyncProductLogDetail asyncProductLogDetail = new AsyncProductLogDetail();
        asyncProductLogDetail.setRequestString(JSON.toJSONString(asyProductCreateGroupRequest));
        asyncProductLogDetail.setAsyncLogTypeEnum(AsyncLogTypeEnum.PRODUCT_GROUP_ASYNC);
        asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.SUCCESS);
        try {
            Map<Integer, AsyProductCreateGroupRequest> asyProductCreateGroupRequestMap = new HashedMap();
            asyProductCreateGroupRequestMap.put(asyProductCreateGroupRequest.getCityId(), asyProductCreateGroupRequest);
            Response<Integer> integerResponse = itemCenterRouterService.asyProductCreateProductGroup(asyProductCreateGroupRequestMap);
            asyncProductLogDetail.setResponseString(JSON.toJSONString(integerResponse));
            return asyncProductLogDetail;
        } catch (Exception ex) {
            log.error("creatProductGroup error", ex);
            asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.Fail);
            asyncProductLogDetail.setResponseString(ex.getMessage());
            return asyncProductLogDetail;
        }
    }


    /**
     * 同步更新规格信息
     *
     * @param specUpdateAsyncQueryRequest
     * @return
     */
    public AsyncProductLogDetail updateProductSpec(SpecUpdateAsyncQueryRequest specUpdateAsyncQueryRequest) {
        AsyncProductLogDetail asyncProductLogDetail = new AsyncProductLogDetail();
        asyncProductLogDetail.setRequestString(JSON.toJSONString(specUpdateAsyncQueryRequest));
        asyncProductLogDetail.setAsyncLogTypeEnum(AsyncLogTypeEnum.SPEC_ASYNC);
        asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.SUCCESS);
        try {

            Map<Integer, SpecUpdateAsyncQueryRequest> asyProductCreateGroupRequestMap = new HashedMap();
            asyProductCreateGroupRequestMap.put(specUpdateAsyncQueryRequest.getCityId(), specUpdateAsyncQueryRequest);
            PlainResult<Boolean> integerResponseMap = itemCenterRouterService.specUpdateAsy(asyProductCreateGroupRequestMap);
            asyncProductLogDetail.setResponseString(JSON.toJSONString(integerResponseMap));
            return asyncProductLogDetail;
        } catch (Exception ex) {
            log.error("creatProductGroup error");
            asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.Fail);
            return asyncProductLogDetail;
        }
    }

    public AsyncProductLogDetail shelveProduct(AsyncProductShelveRequest asyncProductShelveRequest) {
        AsyncProductLogDetail asyncProductLogDetail = new AsyncProductLogDetail();
        asyncProductLogDetail.setRequestString(JSON.toJSONString(asyncProductShelveRequest));
        asyncProductLogDetail.setAsyncLogTypeEnum(asyncProductShelveRequest.getStatus() == 1 ? AsyncLogTypeEnum.PRODUCT_SHELVE_ON :
                AsyncLogTypeEnum.PRODUCT_SHELVE_OFF);
        asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.SUCCESS);
        try {
            Map<Integer, AsyncProductShelveRequest> asyncProductShelveRequestMap = new HashedMap();


            FullCityItemWithDetailQueryRequest request = new FullCityItemWithDetailQueryRequest();
            request.setItemId(asyncProductShelveRequest.getItemId());
            request.setCityId(asyncProductShelveRequest.getCityId().longValue());
            ICResponse<FullCityItemWithDetailInfo> fullCityItemWithDetailInfoICResponse =
                    cityItemReadFacade.queryFullCityItemWithDetail(request);
            // TODO: 2019-11-08 填充多个规格信息
            if (fullCityItemWithDetailInfoICResponse.isSuccess()) {
                FullCityItemWithDetailInfo result = fullCityItemWithDetailInfoICResponse.getResult();


                List<AsyncProductShelveRequest.SkuSpec> skuSpecs = result.getSkuInfoList().stream().filter(CitySkuInfo::getEnable).map(citySkuInfo -> {
                    AsyncProductShelveRequest.SkuSpec skuSpec = new AsyncProductShelveRequest.SkuSpec();
                    if (MapUtils.isNotEmpty(citySkuInfo.getExtraPrice())) {
                        if (citySkuInfo.getExtraPrice().get("avgPrice") != null) {
                            Integer avgPrice = citySkuInfo.getExtraPrice().get("avgPrice").intValue();
                            skuSpec.setAvgPrice(avgPrice * 0.01D);
                        }
                        if (citySkuInfo.getExtraPrice().get("disablePrice") != null) {
                            skuSpec.setDisablePrice(citySkuInfo.getExtraPrice().get("disablePrice").doubleValue() * 0.01);
                        }
                    }
                    skuSpec.setBarcode(citySkuInfo.getBarcode());
                    skuSpec.setThreshold(citySkuInfo.getThreshold());
                    skuSpec.setPrice(citySkuInfo.getPrice().doubleValue() * 0.01D);
                    if (citySkuInfo.getSalesType() != null) {
                        skuSpec.setSaleType(citySkuInfo.getSalesType().getValue());
                    }
                    skuSpec.setSkuId(citySkuInfo.getSkuId());
                    return skuSpec;

                }).collect(Collectors.toList());

                asyncProductShelveRequest.setSkuSpecList(skuSpecs);
            }


            asyncProductShelveRequestMap.put(asyncProductShelveRequest.getCityId(), asyncProductShelveRequest);
            PlainResult<Boolean> booleanPlainResult = itemCenterRouterService.asyncProductShelve(asyncProductShelveRequestMap);
            asyncProductLogDetail.setResponseString(JSON.toJSONString(booleanPlainResult));
            return asyncProductLogDetail;

        } catch (Exception ex) {
            log.error(String.format("updateCityProductStatus error ,request :%s", JSON.toJSONString(asyncProductShelveRequest)), ex);
            asyncProductLogDetail.setAsyStatusEnum(AsyStatusEnum.Fail);
            return asyncProductLogDetail;
        }
    }


    public String getLv2Lv2Names(Long categoryId) {

        String l1L2Names = "";
        ICResponse<List<BackCategoryInfo>> ancestorsOfCategory = backCategoryReadFacade.findAncestorsOf(BackCategoryQueryRequest.builder().id(categoryId).build());

        if (ancestorsOfCategory != null && ancestorsOfCategory.isSuccess()) {
            List<BackCategoryInfo> result = ancestorsOfCategory.getResult();
            if (CollectionUtils.isNotEmpty(result)) {
                Optional<BackCategoryInfo> categoryInfoOptional = result.stream().filter(t -> Objects.equals(t.getId(), categoryId)).findFirst();
                if (categoryInfoOptional.isPresent()) {
                    BackCategoryInfo categoryInfo = categoryInfoOptional.get();
                    String categoryName = categoryInfo.getName();
                    Optional<BackCategoryInfo> pCategory = result.stream().filter(t -> Objects.equals(t.getId(), categoryInfo.getPid())).findFirst();
                    String pCategoryName = "";
                    if (pCategory.isPresent()) {
                        pCategoryName = pCategory.get().getName();
                    }
                    l1L2Names = pCategoryName.concat("_").concat(categoryName);
                }
            }
        }
        return l1L2Names;
    }

    public Map<Integer, FullCityItemWithDetailInfo> listFullCityItemWithDetailInfo(AsyProductRequest request) {
        Map<Integer, FullCityItemWithDetailInfo> citySkuMap = new HashedMap();
        for (Long cityId : request.getCityIdList()) {
            FullCityItemWithDetailQueryRequest fullCityItemWithDetailQueryRequest = new FullCityItemWithDetailQueryRequest();
            fullCityItemWithDetailQueryRequest.setCityId(cityId);
            fullCityItemWithDetailQueryRequest.setItemId(request.getItemIds());
            ICResponse<FullCityItemWithDetailInfo> fullCityItemWithDetailInfoICResponse = cityItemReadFacade.queryFullCityItemWithDetail(fullCityItemWithDetailQueryRequest);
            if (fullCityItemWithDetailInfoICResponse.isSuccess()) {
                citySkuMap.put(cityId.intValue(), fullCityItemWithDetailInfoICResponse.getResult());
            }
        }
        return citySkuMap;
    }

    public List<HqProductTax> findTaxList(List<String> taxCodeList) {
        List<HqProductTax> productTaxesByClassifyCode = tblProductTaxMapper.getProductTaxesByClassifyCode(taxCodeList);
        if (CollectionUtils.isNotEmpty(productTaxesByClassifyCode)) {
            return productTaxesByClassifyCode;
        }
        return Lists.newArrayList();
    }

}
