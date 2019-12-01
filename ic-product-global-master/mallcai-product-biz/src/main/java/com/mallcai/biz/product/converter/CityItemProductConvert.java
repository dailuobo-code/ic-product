package com.mallcai.biz.product.converter;

import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.dailuobo.ic.dto.spec.SkuAttributeDTO;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mallcai.domain.enums.AuditStatus;
import com.mallcai.domain.product.dto.ProductCreateAsyDto;
import com.mallcai.domain.product.request.AddProductRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.*;
import com.mallcai.itemcenter.item.api.bean.response.sku.CitySkuInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuAttributeInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import com.mallcai.itemcenter.sku.enums.SalesType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CityItemProductConvert {

    public static List<ProductCreateAsyDto> convert2Request(FullCityItemWithDetailInfo fullCityItemWithDetailInfo) {
        CityItemInfo itemInfo = fullCityItemWithDetailInfo.getItemInfo();
        CityItemDetailInfo itemDetailInfo = fullCityItemWithDetailInfo.getItemDetailInfo();
        Integer storeType = itemInfo.getStoreType();
        Integer pickType = itemInfo.getPickType();
        Long categoryId = itemInfo.getCategoryId();
        Long createBy = itemInfo.getUpdatedBy();
        Date createTime = itemInfo.getCreateTime();
        List<String> picList = itemDetailInfo.getImages().stream().map(ImageInfo::getUrl).collect(Collectors.toList());
        Long sellerId = itemInfo.getSellerId();
        List<OtherAttributeInfo> otherAttributes = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(itemInfo.getOtherAttributes())) {
            otherAttributes = itemInfo.getOtherAttributes().get(0).getOtherAttributes();
        }
        itemInfo.getOtherAttributes().get(0).getOtherAttributes();
        List<CitySkuInfo> skuParamList = fullCityItemWithDetailInfo.getSkuInfoList();
        List<ProductCreateAsyDto> productCreateAsyDtoList = new ArrayList<>();
        String tax = itemInfo.getTax();
        BigDecimal taxVal = new BigDecimal(tax);
        for (CitySkuInfo skuInfo : skuParamList) {
            ProductCreateAsyDto asyDto = new ProductCreateAsyDto();
            String name = skuInfo.getName();
            if (CollectionUtils.isNotEmpty(skuInfo.getAttributes())) {
                for (SkuAttributeInfo skuAttributeInfo : skuInfo.getAttributes()) {
                    name = name.concat(" ").concat(skuAttributeInfo.getAttrVal());
                }
            }
            String length = "";
            String wides = "";
            String high = "";
            String numUnit = "";
            String origin = "";
            if (CollectionUtils.isNotEmpty(otherAttributes)) {
                length = getOtherAttributeValue(otherAttributes, "长");
                wides = getOtherAttributeValue(otherAttributes, "宽");
                high = getOtherAttributeValue(otherAttributes, "高");
                numUnit = getOtherAttributeValue(otherAttributes, "计量单位");
                origin = getOtherAttributeValue(otherAttributes, "商品产地");

            }
            AddProductRequest hqProduct = AddProductRequest.builder().productName(name)
                    .productIcon(picList.get(0))
                    .productNo(skuInfo.getSkuCode())
                    .barCode(skuInfo.getBarcode())
                    .productPics(picList)
                    .categoryId(categoryId.intValue())
                    .pickClassify(pickType)
                    .keepType(storeType)
                    .remark(itemDetailInfo.getRemark())
                    .userId(MoreObjects.firstNonNull(createBy, 0).intValue())
                    .skuId(skuInfo.getSkuId())
                    .itemId(skuInfo.getItemId())
                    .newArrivalType(itemInfo.getNewArrivalType())
                    .deliveryMode(itemInfo.getDeliveryMode())
                    .seasonal(itemInfo.getSeasonal())
                    .isForecast(0)
                    .isStandard(itemInfo.getIsStandard())
                    .qualityTime(itemInfo.getQualityTime())
                    .pickClassify(itemInfo.getPickType())
                    .productRate(taxVal)
                    .length(length)
                    .high(high)
                    .width(wides)
                    .productType(itemInfo.getType())
                    .arrivalDay(itemInfo.getArrivalDay() == null ? 0 : itemInfo.getArrivalDay())
                    .auditStatus(AuditStatus.APPROVAL)
                    .build();

            asyDto.setAddProductRequest(hqProduct);

            CityProductCreateRequest cityProduct = new CityProductCreateRequest();
            // TODO: 2019-10-21  check

            cityProduct.setProductNo(skuInfo.getSkuCode());

            List<SkuAttributeDTO> attributes = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(skuInfo.getAttributes())) {
                skuInfo.getAttributes().forEach(r -> {
                    SkuAttributeDTO attributeDTO = new SkuAttributeDTO();
                    BeanUtils.copyProperties(r, attributeDTO);
                    attributes.add(attributeDTO);
                });
            }
            if (CollectionUtils.isNotEmpty(otherAttributes)) {
                cityProduct.setLength(length);
                cityProduct.setWides(wides);
                cityProduct.setHigh(high);
                cityProduct.setNumUnit(numUnit);
            }


            cityProduct.setAttributes(attributes);
            cityProduct.setCityProductIcon(picList.get(0));
            cityProduct.setChangeFlag(1);
            cityProduct.setCityStatus(skuInfo.getStatus());
            cityProduct.setSkuId(skuInfo.getSkuId());
            cityProduct.setItemId(skuInfo.getItemId());
            cityProduct.setDeliveryMode(itemInfo.getDeliveryMode());
            //送货上门
            if (Objects.equals(cityProduct.getDeliveryMode(), 2)) {
                cityProduct.setArrivalDay(itemInfo.getArrivalDay() == null ? 0 : itemInfo.getArrivalDay());
            }
            cityProduct.setKeepType(storeType);
            cityProduct.setMerchantId(itemInfo.getSellerId().intValue());
            cityProduct.setIsMultiProduct(1);
            cityProduct.setCreateUserId(createBy.intValue());
            cityProduct.setCreateTime(createTime);
            cityProduct.setUpdateTime(createTime);
            cityProduct.setUpdateUserId(createBy.intValue());
            cityProduct.setShowName(name);
            List<String> detailPicUrlList = itemDetailInfo.getDetail().stream().map(ItemDetailContentInfo::getContent).collect(Collectors.toList());
            cityProduct.setCityProductPic(String.join("^", picList));
            cityProduct.setDetailUrl(String.join("^", detailPicUrlList));
            cityProduct.setDisablePrice(skuInfo.getExtraPrice().get("disablePrice").doubleValue() * 0.01);
            cityProduct.setAdvisePrice(cityProduct.getDisablePrice());
            cityProduct.setQualityTime(itemInfo.getQualityTime());
            cityProduct.setWeightUnit("克");
            cityProduct.setHomeFlag(0);
            cityProduct.setKeyword(itemInfo.getKeywords());
            cityProduct.setSubtitle(itemInfo.getAdvertise());
            cityProduct.setBarCode(skuInfo.getBarcode());
            cityProduct.setPointMallStatus(0);
            cityProduct.setIsForecast(0);
            cityProduct.setIsGoodsRel(0);
            cityProduct.setGoodsType(1);
            cityProduct.setIsShare(1);
            cityProduct.setVipCount(1D);
            cityProduct.setVipLimit(1);
            cityProduct.setThreshold(skuInfo.getThreshold());
            cityProduct.setNewArrivalType(itemInfo.getNewArrivalType());
            cityProduct.setHqStatus(1);
            cityProduct.setCityId(skuInfo.getCityId().intValue());

            if (!Objects.equals(itemInfo.getStatus(), 1)) {
                cityProduct.setCityStatus(2);
            } else {
                if (Objects.equals(skuInfo.getStatus(), 1)) {
                    cityProduct.setCityStatus(1);
                } else {
                    cityProduct.setCityStatus(2);
                }
            }

            cityProduct.setDeliveryMode(itemInfo.getDeliveryMode());
            cityProduct.setSeasonal(itemInfo.getSeasonal());
            cityProduct.setOrigin(origin);
            cityProduct.setQualityTime(itemInfo.getQualityTime());

            Spec spec = new Spec();

            spec.setAvgPrice(MoreObjects.firstNonNull(skuInfo.getExtraPrice().get("avgPrice"), 0).doubleValue() * 0.01);
            // TODO: 2019-11-13 确认
            // spec.setChangeFlag();
            spec.setRealPrice(MoreObjects.firstNonNull(skuInfo.getPrice(), 0).doubleValue() * 0.01D);
            spec.setStoreId(0);
            spec.setCityId(cityProduct.getCityId());
            if (skuInfo.getSalesType() == SalesType.WEIGHT) {
                spec.setUnitType((byte) 1);
                spec.setAvgUnit("斤");
            } else {
                spec.setUnitType((byte) 2);
                spec.setAvgUnit(cityProduct.getNumUnit());
            }
            // TODO: 2019-11-15 packageWeight  逻辑重新梳理
            spec.setPackageMaxWeight(MoreObjects.firstNonNull(skuInfo.getPackageWeight(), 1));
            spec.setPackageQuantity(MoreObjects.firstNonNull(skuInfo.getPackageQuantity(), 0));
            spec.setThreshold(skuInfo.getThreshold());

            spec.setStandardFlag(itemInfo.getIsStandard());
            spec.setVipCount(1D);
            spec.setVipProductPrice(skuInfo.getPrice() * 0.01D);
            spec.setCreateUserId(createBy.intValue());
            cityProduct.setSpec(spec);
            asyDto.setCityProductCreateRequest(cityProduct);
            productCreateAsyDtoList.add(asyDto);
        }
        return productCreateAsyDtoList;
    }


    private String getAttributeValue(List<com.mallcai.domain.product.dto.SkuAttributeDTO> skuAttributeDTOS, String key) {
        Optional<com.mallcai.domain.product.dto.SkuAttributeDTO> first = skuAttributeDTOS.stream().filter(t -> Objects.equals(t.getAttrKey(), key)).findFirst();

        if (first.isPresent()) {
            return first.get().getAttrVal();
        }
        return "";
    }


    private static String getOtherAttributeValue(List<OtherAttributeInfo> otherAttributeInfos, String key) {
        Optional<OtherAttributeInfo> first = otherAttributeInfos.stream().filter(t -> Objects.equals(t.getAttrKey(), key)).findFirst();

        if (first.isPresent()) {
            return first.get().getAttrVal();
        }
        return "";
    }
}

