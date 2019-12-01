package com.mallcai.itemcenter.item.api.converter.output;

import com.mallcai.itemcenter.base.converter.BaseItemConverter;
import com.mallcai.itemcenter.item.api.bean.response.item.*;
import com.mallcai.itemcenter.item.api.bean.response.sku.CitySkuInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import com.mallcai.itemcenter.item.model.*;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.sku.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * CityItemInfoConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 16:06<br/>
 */
@Mapper
public interface CityItemInfoConverter extends BaseItemConverter {
    @Mapping(source = "id", target = "itemId")
    CityItemInfo domain2dto(ItemInfo s);

    @Mapping(source = "id", target = "skuId")
    CitySkuInfo domain2dto(SkuInfo s);

    @Mapping(source = "id", target = "itemDetailId")
    CityItemDetailInfo domain2dto(ItemDetailInfo s);

    void copy(CityItem s, @MappingTarget CityItemInfo t);

    void copy(CitySku s, @MappingTarget CitySkuInfo t);

    default FullCityItemInfo getWithImgs(FullCityItemBO fullCityItemBO, FullItemBO fullItemBO) {return null;}

    default FullCityItemWithDetailInfo getWithDetail(FullCityItemBO fullCityItemBO, FullItemBO fullItemBO) {return null;}

    default CityItemInfo domain2dto(Item item, CityItem cityItem) {
        return null;
    }

    default List<CitySkuInfo> domain2dto(List<Sku> skus, List<CitySku> citySkus) {
        return null;
    }

    default CityItemDetailInfo domain2dto(ItemDetail itemDetail, Long cityId) {
        return null;
    }
}
