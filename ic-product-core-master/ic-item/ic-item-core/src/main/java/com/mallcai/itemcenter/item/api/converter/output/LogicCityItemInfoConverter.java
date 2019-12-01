package com.mallcai.itemcenter.item.api.converter.output;

import com.google.common.collect.Lists;
import com.mallcai.itemcenter.item.api.bean.response.item.*;
import com.mallcai.itemcenter.item.api.bean.response.sku.CitySkuInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.item.model.*;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.sku.model.Sku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * LogicCityItemInfoConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 16:09<br/>
 */
@Slf4j
@Component
public class LogicCityItemInfoConverter extends CityItemInfoConverterImpl implements CityItemInfoConverter {
    private final SkuInfoConverter skuInfoConverter;
    private final ItemInfoConverter itemInfoConverter;

    public LogicCityItemInfoConverter(SkuInfoConverter skuInfoConverter,
                                      ItemInfoConverter itemInfoConverter) {
        this.skuInfoConverter = skuInfoConverter;
        this.itemInfoConverter = itemInfoConverter;
    }

    public FullCityItemWithDetailInfo getWithDetail(FullCityItemBO fullCityItemBO, FullItemBO fullItemBO) {
        if (fullCityItemBO == null || fullItemBO == null) {
            return null;
        }

        FullCityItemWithDetailInfo info = new FullCityItemWithDetailInfo();
        info.setItemInfo(domain2dto(fullItemBO.getItem(), fullCityItemBO.getCityItem()));
        info.setSkuInfoList(domain2dto(fullItemBO.getSkuList(), fullCityItemBO.getCitySkus()));
        info.setItemDetailInfo(domain2dto(fullItemBO.getItemDetail(), info.getCityId()));
        return info;
    }

    public FullCityItemInfo getWithImgs(FullCityItemBO fullCityItemBO, FullItemBO fullItemBO) {
        if (fullCityItemBO == null || fullItemBO == null) {
            return null;
        }

        FullCityItemInfo info = new FullCityItemInfo();
        info.setItemInfo(domain2dto(fullItemBO.getItem(), fullCityItemBO.getCityItem()));
        info.setSkuInfoList(domain2dto(fullItemBO.getSkuList(), fullCityItemBO.getCitySkus()));
        info.setImageList(domain2dto(fullItemBO.getItemDetail().getImages()));
        return info;
    }

    @Override
    public CityItemInfo domain2dto(Item item, CityItem cityItem) {
        if (item == null || cityItem == null) {
            return null;
        }

        // 处理商品
        ItemInfo itemInfo = itemInfoConverter.domain2dto(item);
        CityItemInfo info = domain2dto(itemInfo);
        copy(cityItem, info);

        return info;
    }

    @Override
    public List<CitySkuInfo> domain2dto(List<Sku> skus, List<CitySku> citySkus) {
        if (CollectionUtils.isEmpty(skus) || CollectionUtils.isEmpty(citySkus)) {
            return Collections.emptyList();
        }

        // 处理SKU
        Map<Long, CitySku> idToCitySku = citySkus.stream()
                .collect(Collectors.toMap(CitySku::getSkuId, Function.identity()));

        List<CitySkuInfo> citySkuInfos = Lists.newArrayList();
        for (Sku sku : skus) {
            if (!idToCitySku.containsKey(sku.getId())) {
                continue;
            }

            SkuInfo skuInfo = skuInfoConverter.domain2dto(sku);
            CitySkuInfo citySkuInfo = domain2dto(skuInfo);
            copy(idToCitySku.get(sku.getId()), citySkuInfo);
            citySkuInfo.setEnable(Objects.equals(citySkuInfo.getStatus(), ItemStatus.ON_SHELF.getValue()));
            citySkuInfos.add(citySkuInfo);
        }
        return citySkuInfos;
    }

    @Override
    public CityItemDetailInfo domain2dto(ItemDetail itemDetail, Long cityId) {
        if (itemDetail == null) {
            return null;
        }

        // 处理详情
        ItemDetailInfo detailInfo = itemInfoConverter.domain2dto(itemDetail);
        CityItemDetailInfo itemDetailInfo = domain2dto(detailInfo);
        itemDetailInfo.setCityId(cityId);
        return itemDetailInfo;
    }
}
